package stepdefinitions;

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
    public void deposits_tablosunda_amount_degeri_ile_arasinda_olan_kayitlari_sorgular(Integer int1, Integer int2) {
        // 3.adim query hazirlama
        String query = LoantechQueries.depositsTablosundan100Ve500arasindakiKayitlariGetirme;
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
}
