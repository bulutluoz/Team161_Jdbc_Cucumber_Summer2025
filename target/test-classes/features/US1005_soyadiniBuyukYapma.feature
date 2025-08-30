
  Feature: US1005 users tablosu soyisim testi

    Scenario: TC05 kullanici soyisimleri buyuk harfe cevirebilmeli

      Given kullanici loantech database'ine baglanir
      When users tablosunda username'i "mabally" olan kaydın soyadini büyük harfe çevirir
      Then Update işlemi sonucunda username'i "mabally" olan kaydın soyisim değerinin büyük harfe dönüştüğünü test eder
      And database baglantisini kapatir

