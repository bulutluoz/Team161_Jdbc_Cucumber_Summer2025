package stepdefinitions;

import com.mysql.cj.protocol.x.ReusableOutputStream;
import io.cucumber.java.en.*;
import manageQueries.LoantechQueries;
import org.testng.Assert;
import utilities.JDBCReusableMethods;

import java.sql.ResultSet;
import java.sql.SQLException;

public class loantechStepdefinitions {
    ResultSet resultSet;

    @Given("kullanici loantech database'ine baglanir")
    public void kullanici_loantech_database_ine_baglanir() {
        JDBCReusableMethods.createMyConnection();
    }

    @When("deposits tablosunda amount degeri {int} ile {int} arasinda olan kayitlari sorgular")
    public void deposits_tablosunda_amount_degeri_ile_arasinda_olan_kayitlari_sorgular(Integer minDeger, Integer maxDeger) {
        // 3.adim query hazirlama
        String query = LoantechQueries.depositsTablosundaBelirliAraliktakiKayitlar(minDeger,maxDeger);
        //4. Adim: SQL sorgusunu çalıştırma ve sonuç setini kaydetme
        // statement'i reusable methods'da olusturuyoruz
        // burada sadece query'yi calistirip, resultset'i kaydedelim

        resultSet = JDBCReusableMethods.executeMyQuery(query);
    }

    @Then("bulunan sonuc sayisinin {int} oldugunu test eder")
    public void bulunan_sonuc_sayisinin_oldugunu_test_eder(Integer expectedSonucSayisi) throws SQLException {
        // 5.Adim: resultSet objesini iterator gibi kullanarak istenen işlemi yapma
        //         4.adimda sorgu calistirildi
        //         ve sorgu sonucunda elde edilen kayitlar resultSet objesine kaydedildi
        //         bu adimda resultSet uzerindeki bilgilerden istenen assertion'i yapmaliyiz

        // resultset iterator gibi calistigindan
        // bir sayac olusturup, iterator her ilerlediginde sayaci 1 artirabiliriz

        int sayac =0;

        while (resultSet.next()){
            sayac++;
        }

        Assert.assertEquals(sayac,expectedSonucSayisi);

    }

    @Then("database baglantisini kapatir")
    public void database_baglantisini_kapatir() {
        JDBCReusableMethods.closeMyConnection();
    }

    @When("kullanici cron_schedules tablosundaki {string} bilgilerini sorgular")
    public void kullanici_cron_schedules_tablosundaki_bilgilerini_sorgular(String sutunAdi) {

        String query = LoantechQueries.cron_schedules_istenenSutunuSorgulama("name");
                        // SELECT name FROM cron_schedules;
        resultSet = JDBCReusableMethods.executeMyQuery(query);
    }

    @Then("ilk iki ismin {string} ve {string} olduğunu doğrular")
    public void ilk_iki_ismin_ve_olduğunu_doğrular(String expectedIlkIsim, String expectedIkinciIsim) throws SQLException {

        resultSet.next(); // ilk kaydin ustunden atlar ve ilk satiri resultSet objesine kaydeder
        String actualIlkIsim = resultSet.getString("name");

        resultSet.next(); // ikinci satiri atlar ve ikinci satirdaki tum bilgileri resultSet'e kaydeder
        String actualIkinciIsim = resultSet.getString("name");

        Assert.assertEquals(actualIlkIsim,expectedIlkIsim);
        Assert.assertEquals(actualIkinciIsim,expectedIkinciIsim);

    }

    @When("subscribers tablosundaki {string} bilgilerini sorgular")
    public void subscribers_tablosundaki_bilgilerini_sorgular(String istenenSutun) {
        // String query = "SELECT email FROM subscribers;"; // bu yazim dinamik degil
        String query = LoantechQueries.subscribersTablosuIstenenSutunSorgusu(istenenSutun);

        resultSet = JDBCReusableMethods.executeMyQuery(query);
    }

    @Then("{string} sutununda {string} kaydının bulunduğunu test eder")
    public void sutununda_kaydının_bulunduğunu_test_eder(String sutunIsmi, String arananEmail) throws SQLException {

        boolean arananEmailBulunduMu = false;

        while (resultSet.next()){

            if (resultSet.getString(sutunIsmi).equalsIgnoreCase(arananEmail) ){
                arananEmailBulunduMu = true;
                break;
            }

        }

        Assert.assertTrue(arananEmailBulunduMu,"aranan kayit tabloda yok");

    }



}
