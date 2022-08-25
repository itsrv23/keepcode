package ru.itsrv23.keepcode_test.task3;

public class TaskThree {
    /*
    //Без импортов и контекста сложновато понять как именно рефакторить
    void processTask(ChannelHandlerContext ctx) {

        CommandType typeToRemove;
        for (Command currentCommand : getAllCommands()) {
            if (currentCommand.getCommandType() == CommandType.REBOOT_CHANNEL) {
                rebootChannel(currentCommand, ctx);
                continue;
            }

            if (!currentCommand.isAttemptsNumberExhausted()) {
                sendUSSDMessage(currentCommand, ctx);
            } else {
                typeToRemove = currentCommand.getCommandType();
                deleteCommand(typeToRemove);
            }
        }
        sendKeepAliveOkAndFlush(ctx);
    }

    private void rebootChannel(Command currentCommand, ChannelHandlerContext ctx) {
        if (!currentCommand.isAttemptsNumberExhausted()) {
            if (currentCommand.isTimeToSend()) {
                sendUSSDMessage(currentCommand, ctx);
            }
        } else {
            typeToRemove = currentCommand.getCommandType();
            deleteCommand(typeToRemove);
        }
    }

    private void sendUSSDMessage(Command currentCommand, ChannelHandlerContext ctx) {
        InetSocketAddress lineAddress = getLineAddress();
        sendCommandToContext(ctx, lineAddress, currentCommand.getCommandText());
        tryProcessUssdMessage(lineAddress);
        Log.ussd.write(String.format("sent: ip: %s; порт: %d; %s",
                lineAddress.getHostString(), lineAddress.getPort(), currentCommand.getCommandText()));
        currentCommand.setSendDate(new Date());
        currentCommand.incSendCounter();
    }

    private tryProcessUssdMessage(InetSocketAddress lineAddress){
        DblIncomeUssdMessage ussd = new DblIncomeUssdMessage(lineAddress.getHostName(),
                lineAddress.getPort(),
                0,
                EnumGoip.getByModel(getGoipModel()),
                currentCommand.getCommandText()
        );
        try {
            AdminController.getInstance().processUssdMessage(ussd, false);
        } catch (Exception ignored) {
            //Залогировать наверное стоит
        }
    }

    private InetSocketAddress getLineAddress() {
        return new InetSocketAddress(getIpAddress(), getUdpPort());
    }
*/

}
