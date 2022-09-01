package ru.itsrv23.keepcode_test.task3;

public class TaskThree {

    //Без импортов и контекста сложновато понять как именно рефакторить
    /*
    void processTask(ChannelHandlerContext ctx) {
        //Получаем все команды, проходимся
        for (Command currentCommand : getAllCommands()) {
            // Исчерпали, удаляем задание
            if (currentCommand.isAttemptsNumberExhausted()) {
                deleteCommand(currentCommand.getCommandType());
                continue;
            }
            //Отправляем
            sendUSSDMessage(ctx, lineAddress, currentCommand);
        }
        sendKeepAliveOkAndFlush(ctx);
    }

    private void sendUSSDMessage(ChannelHandlerContext ctx, Command currentCommand) {
        InetSocketAddress lineAddress = new InetSocketAddress(getIpAddress(), getUdpPort());
        // Если ребут и не отправляем
        if(currentCommand.getCommandType() == CommandType.REBOOT_CHANNEL && !currentCommand.isTimeToSend()){
            return;
        }
        var command = currentCommand.getCommandText();
        sendCommandToContext(ctx, lineAddress, command);
        currentCommand.setSendDate(new Date());
        Log.ussd.write(String.format("sent: ip: %s; порт: %d; %s",
                lineAddress.getHostString(), lineAddress.getPort(), command));
        currentCommand.incSendCounter();
        try {
            AdminController.getInstance().processUssdMessage(
                    new DblIncomeUssdMessage(lineAddress.getHostName(), lineAddress.getPort(), 0,
                            EnumGoip.getByModel(getGoipModel()), command), false);

        } catch (Exception ignored) {
            // Можем залогировать
        }
    }

*/
}
