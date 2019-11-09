package com.avaj.simulator;

import java.io.*;

public class MsgLog {

    private static Writer writer = null;

        public static void setOutputFile(String filename) throws IOException
        {
            if (MsgLog.writer != null)
                MsgLog.writer.close();
            MsgLog.writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filename), "utf-8"));
        }

        public static void log(String message)
        {
            try
            {
                MsgLog.writer.write(message + '\n');
                writer.flush();
            }
            catch (IOException e)
            {
                System.out.println("Error while trying to write to the file");
                System.exit(1);
            }
        }
}
