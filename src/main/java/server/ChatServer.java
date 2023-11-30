package server;

public class ChatServer implements ServerListener {
    boolean isServerWorking;
    private final Listener listener;

    public ChatServer(Listener listener) {
        this.listener = listener;
        isServerWorking = false;
    }

    public void run() {
        if (!isServerWorking) {
            isServerWorking = true;
            listener.messageReceive("Server status " + String.valueOf(isServerWorking));
        } else {
            listener.messageReceive("Server already running");
        }
    }
    public void stop() {
        if (isServerWorking) {
            isServerWorking = false;
            listener.messageReceive("Server status " + String.valueOf(isServerWorking));
        }else {
            listener.messageReceive("Server already stopped");
        }

    }

    @Override
    public void listenKey(boolean status) {
        if (status) {
            run();
        } else {
            stop();
        }
    }
}