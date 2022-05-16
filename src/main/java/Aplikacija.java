import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Aplikacija
{
    public static void main(String[] args)
    {
        SeznamiUV seznamiUV = new SeznamiUV();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input;
        String output;

        try
        {
            do
            {
                System.out.print("command> ");
                input = br.readLine();
                output = seznamiUV.processInput(input);

                //procesiranje dodajanja
                if (output.equals("add> EMSO: ")) {
                    System.out.print(output);
                    String emso = br.readLine();
                    output = seznamiUV.processEmso(emso);
                    if (output.equals("add> NAME: ")) {
                        System.out.print(output);
                        String ime = br.readLine();
                        output = seznamiUV.processIme(ime);
                        if (output.equals("add> SURNAME: ")) {
                            System.out.print(output);
                            String priimek = br.readLine();
                            output = seznamiUV.processPriimek(ime, priimek);
                            if (output.equals("add> AGE: ")) {
                                System.out.print(output);
                                String starost = br.readLine();
                                output = seznamiUV.processStarost(starost);
                                if (output.equals("add> VACCINE: ")) {
                                    System.out.print(output);
                                    String cepivo = br.readLine();
                                    output = seznamiUV.processCepivo(cepivo);
                                    if (output.equals("")) {
                                        output = seznamiUV.processAddDrzavljan(emso, ime, priimek, starost, cepivo);
                                    }
                                }
                            }
                        }
                    }
                }

                //procesiranje večkoračnega remove
                if (output.equals("remove> NAME: ")) {
                    System.out.print(output);
                    String ime = br.readLine();
                    output = seznamiUV.processRemoveIme(ime);
                    if (output.equals("remove> SURNAME: ")) {
                        System.out.print(output);
                        String priimek = br.readLine();
                        output = seznamiUV.processRemovePriimek(ime, priimek);
                    }
                }

                //procesiranje večkoračnega search
                if (output.equals("search> NAME: ")) {
                    System.out.print(output);
                    String ime = br.readLine();
                    output = seznamiUV.processSearchIme(ime);
                    if (output.equals("search> SURNAME: ")) {
                        System.out.print(output);
                        String priimek = br.readLine();
                        output = seznamiUV.processSearchPriimek(ime, priimek);
                    }
                }

                //procesiranje reset
                if (output.equals("reset> Are you sure (y|n): ")) {
                    System.out.print(output);
                    String response = br.readLine();
                    output = seznamiUV.processReset(response);
                }

                if (!output.equals("")) {
                    System.out.println(output);
                }
            }
            while (!input.equalsIgnoreCase("exit"));
        }
        catch (IOException e)
        {
            System.err.println("Failed to retrieve the next command.");
            System.exit(1);
        }
    }
}
