package org.example.repository;

import org.example.domain.Drug;

import static org.junit.jupiter.api.Assertions.*;

class InMemoryRepositoryTest {

    @org.junit.jupiter.api.Test
    void create() throws Exception {
        // setup (arrange)
        IRepository<Drug> inMemoryRepository = new InMemoryRepository<>();
        Drug drug1 = new Drug(1, 1, 3, "aaa", "AAA", true);
        Drug drug2 = new Drug(2, 2, 5, "bbb", "BBB", true);
        Drug drug3 = new Drug(3, 3, 6, "ccc", "CCC", true);

        // act
        inMemoryRepository.create(drug1);

        // assert
        assertEquals(1, inMemoryRepository.read().size(), "After adding a drug, read().size() != 1!");
        assertEquals(drug1.getIdEntity(), inMemoryRepository.read().get(0).getIdEntity());

        inMemoryRepository.create(drug2);
        assertEquals(2, inMemoryRepository.read().size(), "After adding 2 drugs, read().size() != 2!");

        // TODO: see assertThrows()
        try {
            inMemoryRepository.create(drug2);
            fail("Adding a drug with existing Id will not give exception!");
        } catch (Exception ex) {
            assertEquals(2, inMemoryRepository.read().size(), "A drug with existing ID was added!");
        }
    }

    @org.junit.jupiter.api.Test
    void readOne() {
    }

    @org.junit.jupiter.api.Test
    void read() {
    }

    @org.junit.jupiter.api.Test
    void update() {
    }

    @org.junit.jupiter.api.Test
    void delete() {
    }
}
