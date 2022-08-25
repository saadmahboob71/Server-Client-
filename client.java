String SERVER_IP = "192.168.10.3";
int SERVER_PORT=8091;
try {
        socket = new Socket(SERVER_IP, SERVER_PORT);
        output = new DataOutputStream(socket.getOutputStream());
        inputStream = new DataInputStream(socket.getInputStream());

        runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        setResultToToast("Connected");
                        Thread2 = new Thread(new Thread2());
                        Thread2.start();
                    }
        });
    } catch (IOException e) {
             setResultToToast(e.getMessage());
   }

class Thread2 implements Runnable {
   @Override
   public void run() {
     int character;
     InputStreamReader reader = new InputStreamReader(inputStream);
     StringBuilder dataa = new StringBuilder();

     while (true) {
         try {

            while((character = reader.read()) != -1 && character != '\n') {
              dataa.append((char) character);
      }
           setResultToToast("Received message is "+ dataa);
           dataa= new StringBuilder();
} catch (Exception e) {
                    setResultToToast("T2 EX -> " + e);
}
}
}
}
class Thread3 implements Runnable {
        private String message;

        Thread3(String message) {
            this.message = message;
        }
        @Override
        public void run() {
            try {
                output.writeUTF(message);
                setResultToToast("M ->>" + message);

            } catch (Exception e) {
                e.printStackTrace();
                setResultToToast("M ERROR->>" + e);
            }
}
}
String message="Hello";
Thread3 = new Thread(new Thread3(message));
                    Thread3.start();
