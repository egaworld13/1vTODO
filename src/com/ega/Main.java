package com.ega;
import java.util.Scanner; // papildelements ,kas nodrošina ievades nolasīšanas funkciju

public class Main { // galvenā klase,

public static String ievade= "1"; // mainīgais nodefinēts, lai aktivizētu while ciklu iekš ievadē.
public static int del;  //public static, lai mainīgā vērtību, kas tiek ierakstīta, izmantojot main metodes argumentus, var būt pielietota child klasē.
public static int ks;  //

    public static void main(String[] args) { // Galvenā metode. void neatgriež iegūtās vērtības ārpus metodes. tāpēc del un ks ir public static.
        Scanner sc = new Scanner(System.in);
        JDBC oper = new JDBC();         // tiek izveidots objekts

        String y, ya, uzd, a, b;

//// UZDEVUMU IZVEIDE

        System.out.println("To Do List!");
                oper.selectid();      // paskatās vai DB  todo tabulā ir kāds ieraksts

        do { // piedāvā veidot uzdevumu kamēr atbilde ir "y", kas norādīts while parametros zem do funkcijas izpildes

            ks++; // kad zemāk redzamā cikla kritēriji izpildās, nakamajam ciklam tiek piešķirt vērtība, kas ir par 1 lielāka kā iepreikšējam ciklam
            ievade = "1"; // katru  ,do cikla izpildes, reizi noreseto ievade vērtību, lai varētu palaist while ciklu.
            while (ievade.length() <= 5) // kritērijs ,kur ievadei ir jābūt ne mazākai  vai vienādai par 5 simboliem
                {
                System.out.println(ks + "." + "Uzdevums (vismaz 5 simboli):");
                    ievade = sc.nextLine();
                }
                oper.insert(); // kad cikls izpildās, izpilās tā kritēriji, tikai tad tiek veikts ieraksts datubāzē

                System.out.println("Vēl vienu uzdevmu? y/n?"); // Piedāvā atkārtot ciklu
                    y = sc.nextLine();

        } while (y.equals("y"));

//// UZDEVUMU APSKATE

            System.out.println("Apskatīt savus uzdevumus? y/n");
                uzd = sc.nextLine();

            if (uzd.equals("y")) {
                oper.select(); // ja uzd vērtība ir "y", tad tiek izsaukta JDBC klases metode select.
            }

//// DELETE

        System.out.println("Vēlaties dzēst uzdevumus? pa vienam = y; visus = n? iziet = e"); // e vietā var būt jebkurš simbols, izņemto y vai n
            a = sc.nextLine();
switch (a){
    case "y": // Dzēš ierakstus pa 1
            System.out.println("Ievadiet uzdevuma nr, lai dzēstu:");
                del = sc.nextInt();
                oper.checkid(); // zem šīs metodes slēpjas funkcija, kas pārbauda vai del ievadītā vērtība atbilst DB ierakstu id kollonas ierasktam(rindai)

            while (del != ks) { // cikkls turpinās, kamēr ievade netabilst ierakstītajam id iekš DB tabulas ar nosaukumu todo
                System.out.println("Jums ir šādi uzdevumi:");
                oper.select(); // atspoguļos DB esošos ierakstus ar id ,kurus var dzēst

                System.out.println("Ievadiet uzdevuma nr, lai dzēstu:");
                    del = sc.nextInt();
                oper.checkid(); // pārbauda vai ievade sakrīt ar DB JDBC tabulas todo id nr iekš cikla, ja nesakrīt, while cikls atkārtojas
            }

            System.out.println("Vēlaties dzēst šo uzdevumu? y/n?:");
                sc.nextLine();
                ya = sc.nextLine();
            if (ya.equals("y"))
                oper.delete();// izsauc JDBC metodi, kas dzēsīs  rindu, iepriekš ievadīto ID iekš mainīgā "del"
    case "n":

            System.out.println("Vēlaties dzēst visus uzdevumus uzreiz? y/n:"); // Piedāvā lietotājam dzēst visus ierakstus tabulā
            b = sc.nextLine();

            if (b.equals("y")) {
                oper.deleteall(); //izsauc metodi,kas dzēš visus ierakstus.
                }
            }
        System.out.println("Uz tikšanos!");
    }
}



