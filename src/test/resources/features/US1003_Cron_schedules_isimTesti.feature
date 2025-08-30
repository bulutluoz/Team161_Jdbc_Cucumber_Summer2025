
  Feature: US1003 cron_schedule name testi

    Scenario:TC03 tabladaki ilk iki isim beklendigi gibi olmali

      Given kullanici loantech database'ine baglanir
      When kullanici cron_schedules tablosundaki "name" bilgilerini sorgular
      Then ilk iki ismin "5 Minutes" ve "10 Minutes" olduğunu doğrular
      And database baglantisini kapatir
