
  Feature: US1004 subscribers tablosu mail testi

    Scenario: TC04 tabloda istenen email bulunmali

      Given kullanici loantech database'ine baglanir
      When subscribers tablosundaki "email" bilgilerini sorgular
      Then "email" sutununda "ayilmaz@gmail.com" kaydının bulunduğunu test eder
      And database baglantisini kapatir
