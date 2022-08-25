<?php
/*  Прием SMS из GOIP
    apt-get install php5-pgsql
*/

$dbconn = pg_connect("host=localhost dbname=taxi2 user=postgres password=GravicapaTolkoZaKace")
    or die('Не удалось соединиться: ' . pg_last_error());


$goip_addr = "http://192.168.0.104:80/"; #Внешний (если работа ведется через интернет) адрес goip. Возможно, придется пробросить порт на роутере, например, 7454 => local_goip_addr:80
$goip_user = "admin";
$goip_password = "admin";

#Это нужно для авторизации на goip-е
$context = stream_context_create(array(
    'http' => array(
        'header'  => "Authorization: Basic " . base64_encode("$goip_user:$goip_password")
    )
));
#получаем сырые данные - html
$data = file_get_contents($goip_addr."/default/en_US/tools.html?type=sms_inbox", false, $context);
#у меня встречались такие косяки, правим
$data = str_replace('\"', '_', $data); //fix
$data = str_replace('\'','_', $data); //fix
$data = str_replace('\\n',' ', $data); //fix

#выдираем содержимое sms из js-скрипта в html, ключ = каналу sim
preg_match_all("|sms= \[(.*?)\]|is", $data, $sms_dump_arr);


$sms = array();
#первый цикл - по "каналам sim", которые содержат 5 смс-ок, разделенных запятой и в кавычках. То есть в csv.
#Во втором цикле мы с помощью str_getcsv добываем валидно данные уже по каждой смс раздельно.
foreach($sms_dump_arr[1] as $sim_key => $sim_val)
  foreach(str_getcsv($sim_val) as $sms_key => $sms_val) {
      $sms[$sim_key][$sms_key] = explode(',', $sms_val, 3); #ключи 0,1,2 содержат датувремя, номер, текст смс.
      $sms[$sim_key][$sms_key][] = md5($sms_val); #ключ 3 содержит хеш первых трех, для упрощения идентификации sms-ки в дальнейшем
        $cnt_arr =  count($sms[$sim_key][$sms_key]);
        if ($cnt_arr == 4) {
        $arrv_dt = $sms[$sim_key][$sms_key][0];
        $arrv_tel = $sms[$sim_key][$sms_key][1];
        $arrv_text = $sms[$sim_key][$sms_key][2];
        $arrv_md5 = $sms[$sim_key][$sms_key][3];
        // Выполнение SQL-запроса
        $query = "SELECT a_samael_check_and_insert_sms('$arrv_tel','$arrv_text','$arrv_md5')";
	echo $query;
        $result = pg_query($query) or die('Ошибка запроса: ' . pg_last_error());
        echo "$result \n";
        }
}
pg_close($dbconn);

#смотрим результат
#print_r($sms);