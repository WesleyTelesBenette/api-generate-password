import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class TestApiPassword
{
    static int security = 0;
    static int amount = 0;
    static String key = "";

    static int responseCode = 0;

    static String responseJson = "";

    public static void main(String[] args)
    {
        showInputs();
        generatePassword();
        if (responseCode == 200)
            showResponse();
    }

    public static void showInputs()
    {
        try (Scanner read = new Scanner(System.in))
        {
            System.out.println("\n----- GERADOR DE SENHAS -----");
            System.out.println("  (Digite o que for pedido)  \n");

            System.out.print(" * Palavra-chave (<9): ");
            key = read.nextLine();

            System.out.print(" * securityLevel (0-3): ");
            security = read.nextInt();

            System.out.print(" * amountCharacters (>7): ");
            amount = read.nextInt();

            System.out.println("\n-----------------------------\n");
        }
    }

    public static void generatePassword()
    {
        try
        {
            //Configurações básicas
            URL url = new URL("https://api-generate-password.onrender.com/password");
            HttpURLConnection Connection = (HttpURLConnection) url.openConnection();
            Connection.setRequestMethod("POST");
            Connection.setRequestProperty("Content-Type", "application/json");
            Connection.setDoOutput(true);

            //Model para o corpo da mensagem
            String requestBody =
                "{ " +
                    "\"securityLevel\": " + security + ", " +
                    "\"amountCharacters\": " + amount + ", " +
                    "\"keyword\": \"" + key + "\"" +
                " }";

            //Enviar requisição junto com o corpo
            try (OutputStream os = Connection.getOutputStream())
            {
                byte[] input = requestBody.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            //Código de resposta
            responseCode = Connection.getResponseCode();
            if (responseCode != 200) {
                throw new RuntimeException("Falha na requisição. Código de resposta: " + responseCode);
            }

            //Pegando a resposta
            try (BufferedReader read = new BufferedReader(new InputStreamReader(Connection.getInputStream())))
            {
                StringBuilder responseContent = new StringBuilder();
                String line;

                while ((line = read.readLine()) != null)
                    responseContent.append(line);

                //Resposta em JSON
                responseJson = responseContent.toString();
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public static void showResponse()
    {
        System.out.println(" SENHA GERADA:");
        System.out.println("     " + responseJson + "\n");
    }
}