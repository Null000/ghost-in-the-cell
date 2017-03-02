import java.io.*;
import java.util.Properties;
import java.util.Scanner;

class RefereeRunner {
    final static int playerCount = 2;
    static Process[] ai = new Process[playerCount];
    static Scanner[] in = new Scanner[playerCount];
    static Scanner[] err = new Scanner[playerCount];
    static BufferedWriter[] out = new BufferedWriter[playerCount];

    public static void main(String[] args) {
        Referee ref = null;
        try {
            for (int player = 0; player < playerCount; player++) {
                ProcessBuilder pb = new ProcessBuilder();
                pb.command(args[player].split(" "));

                Process pr = pb.start();
                ai[player] = pr;
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(pr.getOutputStream()));
                out[player] = writer;
                err[player] = new Scanner(pr.getInputStream());
                in[player] = new Scanner(pr.getErrorStream());
            }

            ref = new Referee(System.in, System.out, System.err);
            Properties prop = new Properties();
            ref.initReferee(playerCount, prop);

            String[][] initialToPlayer = new String[playerCount][];
            for (int player = 0; player < playerCount; player++) {
                initialToPlayer[player] = ref.getInitInputForPlayer(player);
            }
            sendToPlayers(initialToPlayer);

            for (int round = 0; round < ref.getMaxRoundCount(playerCount); round++) {
//                System.out.println(round);
                String[][] toPlayer = new String[playerCount][];
                for (int player = 0; player < playerCount; player++) {
                    toPlayer[player] = ref.getInputForPlayer(round, player);
                }
                sendToPlayers(toPlayer);
                String[][] fromPlayer = new String[playerCount][];
                for (int player = 0; player < playerCount; player++) {
//                    System.out.println(ai[player].isAlive());
                    String line = in[player].nextLine();
//                    System.out.println(line);
                    fromPlayer[player] = new String[]{line};
                }

                for (int player = 0; player < playerCount; player++) {
                    ref.handlePlayerOutput(0, round, player, fromPlayer[player]);
                }
                ref.updateGame(round);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(ref != null) {
                for (int player = 0; player < playerCount; player++) {
                    System.out.println(ref.getScore(player));
                }
            }
        }
    }

    private static void sendToPlayers(String[][] dataToPlayer) throws IOException {
        for (int player = 0; player < playerCount; player++) {
            String toSend = String.join("\n", dataToPlayer[player]) + "\n";
            if (player == 0) {
//                System.out.print(toSend);
            }
            out[player].write(toSend);
            out[player].flush();
        }
    }
}