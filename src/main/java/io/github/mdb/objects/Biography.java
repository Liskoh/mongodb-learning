package io.github.mdb.objects;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Biography {

    private UUID uuid;
    private String biographyName;
    private String biographyDescription;
    private Map<String, String> biographyData;

    public Biography(String biographyName, String biographyDescription) {
        this.uuid = UUID.randomUUID();
        this.biographyName = biographyName;
        this.biographyDescription = biographyDescription;
        this.biographyData = new HashMap<>();

        this.fillWithRandomData(5);
    }

    public void fillWithRandomData(int amount) {
        String randomString = UUID.randomUUID().toString() + UUID.randomUUID().toString();

        for (int i = 0; i < amount; i++) {
            this.biographyData.put(UUID.randomUUID().toString(), randomString);
        }
    }

    public void addData(String key, String value) {
        this.biographyData.put(key, value);
    }
}
