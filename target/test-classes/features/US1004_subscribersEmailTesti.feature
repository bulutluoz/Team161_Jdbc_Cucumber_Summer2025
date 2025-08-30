
  Feature: US1004 subscribers tablosu mail testi

    Scenario: TC04 tabloda istenen email bulunmali

      Given kullanici loantech database'ine baglanir
      When subscribers tablosundaki "id" bilgilerini sorgular
      Then "id" sutununda "117" kaydının bulunduğunu test eder
      And database baglantisini kapatir
