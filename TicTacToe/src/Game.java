import java.util.*;

public class Game {

    static ArrayList<Integer> spielerPositions = new ArrayList<>();
    static ArrayList<Integer> cpuPositions = new ArrayList<>();


    public static void main(String[] args) {
        char[][] brett = {
                {' ','|',' ','|',' '},
                {'-','+','-','+','-'},
                {' ','|',' ','|',' '},
                {'-','+','-','*','-'},
                {' ','|',' ','|',' '}
        };

        printBrett(brett);



        while(true) {
            Scanner scan = new Scanner(System.in);
            System.out.println("Position: ");
            int spielerPosition = scan.nextInt();

            while (spielerPositions.contains(spielerPosition) || cpuPositions.contains(spielerPosition)){
                System.out.println("Postion bereits vergeben");
                spielerPosition = scan.nextInt();
            }

            eingabe(brett, spielerPosition, "spieler");
            if(gewinner().length()>0){
                System.out.println(gewinner());
                break;
            }

            Random random = new Random(); // Durch Logik ersetzen
            int cpuPosition = random.nextInt(9) + 1;

            while (spielerPositions.contains(cpuPosition) || cpuPositions.contains(cpuPosition)){
                cpuPosition = scan.nextInt();
            }
            eingabe(brett, cpuPosition, "cpu");

            if(gewinner().length()>0){
                System.out.println(gewinner());
                break;
            }
            printBrett(brett);
        }

    }

    public static void printBrett(char[][] brett) {
        for(char[] reihe : brett) {
            for (char c : reihe) {
                System.out.print(c);
            }
            System.out.println();
        }
        System.out.println("  ");
        System.out.println("----------");
        System.out.println("  ");
    }

    public static void eingabe(char[][] brett, int position, String user) {

        char zeichen = ' ';

        if (user.equals("spieler")){
            zeichen = 'X';
            spielerPositions.add(position);
        }else if(user.equals("cpu")){
            zeichen = 'O';
            cpuPositions.add(position);
        }

        switch (position) {
            case 1:
                brett[0][0] = zeichen;
                break;
            case 2:
                brett[0][2] = zeichen;
                break;
            case 3:
                brett[0][4] = zeichen;
                break;
            case 4:
                brett[2][0] = zeichen;
                break;
            case 5:
                brett[2][2] = zeichen;
                break;
            case 6:
                brett[2][4] = zeichen;
                break;
            case 7:
                brett[4][0] = zeichen;
                break;
            case 8:
                brett[4][2] = zeichen;
                break;
            case 9:
                brett[4][4] = zeichen;
                break;

            default:
                throw new IllegalStateException("Unexpected value: " + position);
        }

        printBrett(brett);
    }

    public static String gewinner() {

        List oben = Arrays.asList(1,2,3);
        List mitteH = Arrays.asList(4,5,6);
        List unten = Arrays.asList(7,8,9);

        List links = Arrays.asList(1,4,7);
        List mitteV = Arrays.asList(2,5,8);
        List rechts = Arrays.asList(3,6,9);

        List diagonal1 = Arrays.asList(7,5,3);
        List diagonal2 = Arrays.asList(1,5,9);

        List<List> gewonnen = new ArrayList<List>();
        gewonnen.add(oben);
        gewonnen.add(mitteH);
        gewonnen.add(unten);
        gewonnen.add(links);
        gewonnen.add(mitteV);
        gewonnen.add(rechts);
        gewonnen.add(diagonal1);
        gewonnen.add(diagonal2);

        for(List l : gewonnen ) {
            if(spielerPositions.containsAll(l)){
                return "Spieler hat gewonnen";
            } else if (cpuPositions.containsAll(l)){
                return "Cpu hat gewonnen";
            }else if(spielerPositions.size() + cpuPositions.size() == 9)
                return "Unentschieden";
        }

        return "";
    }
}
