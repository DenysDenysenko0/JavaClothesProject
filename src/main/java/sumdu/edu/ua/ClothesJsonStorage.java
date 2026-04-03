package sumdu.edu.ua;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import sumdu.edu.ua.enums.Size;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Клас для збереження та завантаження одягу з JSON.
 */
public class ClothesJsonStorage {

    public static List<Clothes> loadFromJson(String fileName) {
        Gson gson = new Gson();
        List<Clothes> clothesList = new ArrayList<Clothes>();

        try {
            FileReader reader = new FileReader(fileName);
            Type listType = new TypeToken<ArrayList<ClothesJsonData>>() {}.getType();
            List<ClothesJsonData> rawList = gson.fromJson(reader, listType);
            reader.close();

            if (rawList == null) {
                return clothesList;
            }

            int i;
            for (i = 0; i < rawList.size(); i++) {
                try {
                    Clothes item = createClothesFromData(rawList.get(i));
                    if (item != null) {
                        clothesList.add(item);
                    }
                } catch (IllegalArgumentException e) {
                    System.out.println("Помилка в рядку JSON: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.out.println("Помилка читання JSON: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Помилка обробки JSON: " + e.getMessage());
        }

        return clothesList;
    }

    public static void saveToJson(List<Clothes> clothesList, String fileName) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        List<ClothesJsonData> rawList = new ArrayList<ClothesJsonData>();

        int i;
        for (i = 0; i < clothesList.size(); i++) {
            rawList.add(createDataFromClothes(clothesList.get(i)));
        }

        try {
            FileWriter writer = new FileWriter(fileName);
            gson.toJson(rawList, writer);
            writer.close();
        } catch (IOException e) {
            System.out.println("Помилка запису JSON: " + e.getMessage());
        }
    }

    private static Clothes createClothesFromData(ClothesJsonData data) {
        Size size = Size.valueOf(data.getSize());

        if ("Clothes".equals(data.getType())) {
            return new Clothes(data.getName(), size, data.getPrice(), data.getBrand(), data.getQuantity());
        }
        if ("Pants".equals(data.getType())) {
            return new Pants(data.getName(), size, data.getPrice(), data.getBrand(), data.getQuantity(), data.getWaistSize());
        }
        if ("Shirts".equals(data.getType())) {
            return new Shirts(data.getName(), size, data.getPrice(), data.getBrand(), data.getQuantity(), data.getSleeveLength());
        }
        if ("Jackets".equals(data.getType())) {
            return new Jackets(data.getName(), size, data.getPrice(), data.getBrand(), data.getQuantity(), data.getHasHood());
        }
        if ("Shoes".equals(data.getType())) {
            return new Shoes(data.getName(), size, data.getPrice(), data.getBrand(), data.getQuantity(), data.getSoleMaterial());
        }

        throw new IllegalArgumentException("Unknown type: " + data.getType());
    }

    private static ClothesJsonData createDataFromClothes(Clothes clothes) {
        ClothesJsonData data = new ClothesJsonData();

        data.setName(clothes.getName());
        data.setSize(clothes.getSize().name());
        data.setPrice(clothes.getPrice());
        data.setBrand(clothes.getBrand());
        data.setQuantity(clothes.getQuantity());

        if (clothes instanceof Pants pants) {
            data.setType("Pants");
            data.setWaistSize(pants.getWaistSize());
        }
        else if (clothes instanceof Shirts shirts) {
            data.setType("Shirts");
            data.setSleeveLength(shirts.getSleeveLength());
        }
        else if (clothes instanceof Jackets jackets) {
            data.setType("Jackets");
            data.setHasHood(jackets.isHasHood());
        }
        else if (clothes instanceof Shoes shoes) {
            data.setType("Shoes");
            data.setSoleMaterial(shoes.getSoleMaterial());
        }
        else {
            data.setType("Clothes");
        }

        return data;
    }
}