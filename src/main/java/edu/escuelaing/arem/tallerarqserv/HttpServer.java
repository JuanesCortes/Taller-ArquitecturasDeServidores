package edu.escuelaing.arem.tallerarqserv;

import java.net.*;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class HttpServer {
    public static void main(String[] args) throws IOException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(35000);
        } catch (IOException e) {
            System.err.println("Could not listen on port: 35000.");
            System.exit(1);
        }
        Socket clientSocket = null;
        while (true) {

            try {
                System.out.println("Listo para recibir ...");
                clientSocket = serverSocket.accept();
            } catch (IOException e) {
                System.err.println("Accept failed.");
                System.exit(1);
            }
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(
                            clientSocket.getInputStream()));
            String inputLine, outputLine;
            String path = "";
            while ((inputLine = in.readLine()) != null) {
                   if (inputLine.contains("GET")){
                       path = inputLine.split(" ")[1];
                   }
                System.out.println("Received: " + inputLine);
                if (!in.ready()) {
                    break;
                }
            }

            if (MicroSpringBoot.existPath(path)){
                outputLine = "HTTP/1.1 200 OK\r\n"
                        + "Content-Type: text/html\r\n"
                        + "\r\n"
                        +getServices(path);

                out.println(outputLine);
            }

            out.close();
            in.close();
            clientSocket.close();

        }
        //serverSocket.close();
    }
    public static String getServices(String path) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException{
        Method met = MicroSpringBoot.getMethod(path);
        return "<!DOCTYPE html>\n" +
                "<html>\n" +
                "    <head>\n" +
                "        <title>"+met.invoke(null)+"</title>\n" +
                "    </head>\n" +
                "</html>";
    }
   
}
