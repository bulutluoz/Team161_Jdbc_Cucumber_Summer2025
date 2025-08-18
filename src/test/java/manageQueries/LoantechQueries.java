package manageQueries;

public class LoantechQueries {

    // bu class'i sorgulari depolamak
    // ve dinamik sorgular olusturmak icin kullanacagiz

    public static String depositsTablosundan100Ve500arasindakiKayitlariGetirme = "SELECT * FROM deposits WHERE amount >= 100 AND amount <= 500;";
}
