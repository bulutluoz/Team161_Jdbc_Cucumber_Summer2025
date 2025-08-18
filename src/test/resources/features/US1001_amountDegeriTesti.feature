
  Feature: US1001 amount miktari ile test yapma

    Scenario:TC01 verilen araliktaki miktarlar beklenene esit olmali

      # Gorev : Loantech uygulamasindaki deposits tablosunda
      # amount değeri 100$ ile 500$ arasinda olan 10 kayit bulunduğunu test edin.

      Given kullanici loantech database'ine baglanir
      When deposits tablosunda amount degeri 100 ile 500 arasinda olan kayitlari sorgular
      Then bulunan sonuc sayisinin 10 oldugunu test eder
      And database baglantisini kapatir
