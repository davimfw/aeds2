import java.io.*;
import java.net.*;

public class TP01Q08 {
    public static void main(String[] args) {
        String title, site;
        boolean end = false;
        while (!end) {
            title = MyIO.readLine();
            end = isFim(title);
            if (!end) {
                site = MyIO.readLine();
                site = getHtml(site);
                createStringOut(title, site);
            }
        }
    }

    public static void createStringOut(String title, String site) {
        int[] characters = new int[25];
        
        for (int i = 0; i < characters.length; i++) {
            characters[i] = 0;
        }
        for (int i = 0; i < site.length(); i++) {
            if (site.charAt(i) == 'a')
                characters[0]++;
            else if (site.charAt(i) == 'e')
                characters[1]++;
            else if (site.charAt(i) == 'i')
                characters[2]++;
            else if (site.charAt(i) == 'o')
                characters[3]++;
            else if (site.charAt(i) == 'u')
                characters[4]++;
            else if (site.charAt(i) == '\u00E1')
                characters[5]++;
            else if (site.charAt(i) == '\u00E9')
                characters[6]++;
            else if (site.charAt(i) == '\u00ED')
                characters[7]++;
            else if (site.charAt(i) == '\u00F3')
                characters[8]++;
            else if (site.charAt(i) == '\u00FA')
                characters[9]++;
            else if (site.charAt(i) == '\u00E0')
                characters[10]++;
            else if (site.charAt(i) == '\u00E8')
                characters[11]++;
            else if (site.charAt(i) == '\u00EC')
                characters[12]++;
            else if (site.charAt(i) == '\u00F2')
                characters[13]++;
            else if (site.charAt(i) == '\u00F9')
                characters[14]++;
            else if (site.charAt(i) == '\u00E3')
                characters[15]++;
            else if (site.charAt(i) == '\u00F5')
                characters[16]++;
            else if (site.charAt(i) == '\u00E2')
                characters[17]++;
            else if (site.charAt(i) == '\u00EA')
                characters[18]++;
            else if (site.charAt(i) == '\u00EE')
                characters[19]++;
            else if (site.charAt(i) == '\u00F4')
                characters[20]++;
            else if (site.charAt(i) == '\u00FB')
                characters[21]++;
            else if (consoante(site.charAt(i)))
                characters[22]++;
            else if (i + 4 < site.length() && site.substring(i, i + 4).equals("<br>")) {
                i += 4;
                characters[23]++;
            } else if (i + 7 < site.length() && site.substring(i, i + 7).equals("<table>")) {
                i += 7;
                characters[24]++;
            }
        }
        MyIO.println("a(" + characters[0] + ") e(" + characters[1] + ") i(" + characters[2] + ") o(" + characters[3]
                + ") u(" + characters[4] + ") á(" + characters[5] + ") é(" + characters[6] + ") í(" + characters[7]
                + ") ó(" + characters[8] + ") ú(" + characters[9] + ") à(" + characters[10] + ") è(" + characters[11]
                + ") ì(" + characters[12] + ") ò(" + characters[13] + ") ù(" + characters[14] + ") ã(" + characters[15]
                + ") õ(" + characters[16] + ") â(" + characters[17] + ") ê(" + characters[18] + ") î(" + characters[19]
                + ") ô(" + characters[20] + ") û(" + characters[21] + ") consoante(" + characters[22] + ") <br>("
                + characters[23] + ") <table>(" + characters[24] + ") " + title);
    }

    public static boolean consoante(char letter) {
        if (letter >= 'a' && letter <= 'z') {
            if (letter == 'a' || letter == 'e' || letter == 'i' || letter == 'o'
                    || letter == 'u') {
                return false;
            }
            return true;
        }
        return false;
    }

    public static String getHtml(String endereco) {
        URL url;
        InputStream is = null;
        BufferedReader br;
        String resp = "", line;

        try {
            url = new URL(endereco);
            is = url.openStream(); // throws an IOException
            br = new BufferedReader(new InputStreamReader(is));

            while ((line = br.readLine()) != null) {
                resp += line + "\n";
            }
        } catch (MalformedURLException mue) {
            mue.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        try {
            is.close();
        } catch (IOException ioe) {
            // nothing to see here

        }

        return resp;
    }

    public static boolean isFim(String word) {
        return word.length() == 3 && word.charAt(0) == 'F' && word.charAt(1) == 'I' && word.charAt(2) == 'M';
    }
}
