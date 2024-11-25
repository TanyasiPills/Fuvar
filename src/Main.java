import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {

        List<Fuvar> list = Read("fuvar.csv");
        Feladatok(list);

        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();

    }

    public static List<Fuvar> Read(String file){
        List<Fuvar> list = new ArrayList<Fuvar>();
        try(BufferedReader br = new BufferedReader(new FileReader(file))){
            String line;
            br.readLine();
            while((line = br.readLine()) != null){
                String[] data = line.split(";");
                data = Arrays.stream(data).map(e -> e.replace(",", ".")).toArray(String[]::new);
                list.add(new Fuvar(data));
            }
        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }

        return list;
    }

    public static void Feladatok(List<Fuvar> list){

        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd");

        System.out.printf("Összesen %d utazás került feljegyzésre\n", list.size());
        System.out.printf("A 6185-ös taxi %d fuvart végzet, keresete: %.2f$\n", list.stream().filter(e-> e.getId() == 6185).count(), list.stream().filter(e-> e.getId() == 6185).mapToDouble(e -> e.getPrice() + e.getTip()).sum());
        System.out.printf("Összesen a taxisok %.2f mérföldet tettek meg\n", list.stream().mapToDouble(Fuvar::getMiles).sum());
        System.out.printf("Leghosszabb útazási idővel rendelkező fuvar: %s\n", list.stream().max(Comparator.comparingDouble(Fuvar::getTime)));
        System.out.printf("Legbőkezübb utas fuvara: %s\n", list.stream().max(Comparator.comparingDouble(e -> e.getTip() / e.getPrice())).orElse(null));
        System.out.printf("A 4261-es taxi összesen %.2fkm utat tett meg\n", list.stream().filter(e-> e.getId() == 4261).mapToDouble(Fuvar::getMiles).sum()*1.6);
        System.out.println("\n"+list.stream().filter(e -> e.getTime() > 0 && e.getPrice() > 0 && e.getMiles() == 0).map(Fuvar::toString).collect(Collectors.joining("\n"))+"\n");
        System.out.println(list.stream().anyMatch(e -> e.getId() == 1452) ? "Az adatok között szereper a 1452-es taxi" : "Az adatok között nem szereper a 1452-es taxi");
        System.out.println("\n"+list.stream().filter(e -> e.getTime() > 0).sorted(Comparator.comparingDouble(Fuvar::getTime)).limit(3).map(Fuvar::toString).collect(Collectors.joining("\n")));
        System.out.printf("December 24.-én %s fuvar volt\n", list.stream().filter(e -> sdf.format(e.getStart()).equals("12-24")).count());
        System.out.printf("%s/%s arányban adtak borravalót az utasok december 31.-én\n", list.stream().filter(e ->sdf.format(e.getStart()).equals("12-24") && e.getTip() > 0).count(), list.stream().filter(e ->sdf.format(e.getStart()).equals("12-24")).count());

    }
}