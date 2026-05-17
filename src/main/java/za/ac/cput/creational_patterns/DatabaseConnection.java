package za.ac.cput.creational_patterns;

public class DatabaseConnection {
    private static volatile DatabaseConnection instance = null;
    private String url;
    private String username;
    private boolean connected;

    private DatabaseConnection() {
        this.url = "jdbc:mysql://localhost:3306/smart_library";
        this.username = "root";
        this.connected = false;
    }

    public static DatabaseConnection getInstance() {
        if (instance == null) {
            synchronized (DatabaseConnection.class) {
                if (instance == null) {
                    instance = new DatabaseConnection();
                }
            }
        }
        return instance;
    }

    public void connect() {
        if (!connected) {
            this.connected = true;
            System.out.println("Connected to database: " + url);
        } else {
            System.out.println("Already connected.");
        }
    }

    public void disconnect() {
        if (connected) {
            this.connected = false;
            System.out.println("Disconnected from database.");
        }
    }

    public boolean isConnected() { return connected; }
    public String getUrl() { return url; }
    public String getUsername() { return username; }
}