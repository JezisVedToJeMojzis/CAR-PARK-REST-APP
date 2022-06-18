package sk.stuba.fei.uim.vsa.pr1b;

import java.io.*;

public class KeyboardInput
{
    public static char readChar(String string_for_user)
    {
        char c = ' ';

        InputStreamReader keyboardInput = new InputStreamReader(System.in);
        try
        {	System.out.println(string_for_user);
            c = (char) keyboardInput.read();
            //System.out.println("Nacital som " + c);
        }
        catch (Exception e)
        {
            System.out.println("Wrong input. Try again...");
            c = readChar(string_for_user);
        }

        return c;
    }

    public static String readString(String string_for_user)
    {
        String s = "";

        BufferedReader keyboardInput = new BufferedReader(new InputStreamReader(System.in));
        try
        {	System.out.println(string_for_user);
            s = keyboardInput.readLine();
            //System.out.println("Nacital som " + s);
        }
        catch (Exception e)
        {
            System.out.println("Wrong input. Try again.");
            s = readString(string_for_user);
        }

        return s;
    }

    public static int readInt(String string_for_user)
    {
        int n = 0;
        String s;

        BufferedReader keyboardInput = new BufferedReader(new InputStreamReader(System.in));
        try
        {	System.out.println(string_for_user);
            s = keyboardInput.readLine();
            //System.out.println("Nacital som " + s);
            n = Integer.parseInt(s);
        }
        catch (Exception e)
        {
            System.out.println("Wrong input. Try again.");
            n = readInt(string_for_user);
        }

        return n;
    }


    public static Long readLong(String string_for_user)
    {
        long l = 0;
        String s;

        BufferedReader keyboardInput = new BufferedReader(new InputStreamReader(System.in));
        try
        {	System.out.println(string_for_user);
            s = keyboardInput.readLine();
            //System.out.println("Nacital som " + s);
            l = Long.parseLong(s);
        }
        catch (Exception e)
        {
            System.out.println("Wrong input. Try again.");
            l = readLong(string_for_user);
        }

        return l;
    }

    public static double readDouble(String string_for_user)
    {
        double x = 0.0;
        String s;

        BufferedReader keyboardInput = new BufferedReader(new InputStreamReader(System.in));
        try
        {	System.out.println(string_for_user);
            s = keyboardInput.readLine();
            //System.out.println("Nacital som " + s);
            x = Double.parseDouble(s);
        }
        catch (Exception e)
        {
            System.out.println("Wrong input. Try again.");
            x = readDouble(string_for_user);
        }

        return x;
    }
}