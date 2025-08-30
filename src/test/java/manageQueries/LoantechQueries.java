package manageQueries;

public class LoantechQueries {

    // bu class'i sorgulari depolamak
    // ve dinamik sorgular olusturmak icin kullanacagiz

    // eger sabit bir query ise String olarak query olusturulur
    // eger dinamik bir sorgu olmasi istenirse String döndüren bir method olarak query olusturulur


    public static String depositsTablosundan100Ve500arasindakiKayitlariGetirme =
            "SELECT * FROM deposits WHERE amount >= 100 AND amount <= 500;";


    public static String depositsTablosundaBelirliAraliktakiKayitlar(int minDeger , int maxDeger){

        return "SELECT * FROM deposits WHERE amount >= "+minDeger+" AND amount <= "+maxDeger+";";
    }

    public static String cron_schedules_istenenSutunuSorgulama(String istenenSutun){
        return "SELECT " + istenenSutun + " FROM cron_schedules;";
    }

    public static String subscribersTablosuIstenenSutunSorgusu(String istenenSutun){
        //    SELECT email FROM subscribers;
        return "SELECT " + istenenSutun + " FROM subscribers;";
    }

    // SELECT lastname FROM users WHERE username = 'mabally';
    public static String usersTablosundaUsernamedenSoyisimSorgusu(String username){
        return "SELECT lastname FROM users WHERE username = '"+username+"';" ;
    }


    // UPDATE users SET lastname = 'mabally' WHERE username = 'mabally';
    public static String usersTablosundaSoyismiUpdateEtmeSorgusu(String username, String yeniSoyisim){
        return "UPDATE users SET lastname = '" + yeniSoyisim + "' WHERE username = '" + username+"';";
    }


}
