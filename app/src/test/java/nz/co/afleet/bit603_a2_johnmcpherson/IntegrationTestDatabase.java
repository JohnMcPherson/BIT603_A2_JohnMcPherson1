package nz.co.afleet.bit603_a2_johnmcpherson;

import android.app.Application;

import androidx.test.core.app.ApplicationProvider;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(RobolectricTestRunner.class)
public class IntegrationTestDatabase {
    private Application application;
    InventoryDatabase inventoryDatabase;
    DaoInventory daoInventory;

    private final String SUGAR = "Sugar";
    private final double SUGAR_QUANTITY = 4;
    private final String FLOUR = "Flour";
    private final double FLOUR_QUANTITY = 6.6;



    @Before
    public void initialiseApplicationInstance() {
        Application application = ApplicationProvider.getApplicationContext();
        inventoryDatabase = InventoryDatabase.getInstance(application);
        daoInventory = inventoryDatabase.daoInventory();
    }

    @Test
    public void testCreateInventoryItem() {
        // test with whole number quantity
        InventoryItem sugarInventory = InventoryItem.create(SUGAR, SUGAR_QUANTITY);
        testInventoryItemContent(sugarInventory, SUGAR, SUGAR_QUANTITY);

        // and test with decimal number quantity
        InventoryItem flourInventory = InventoryItem.create(FLOUR, FLOUR_QUANTITY);
        testInventoryItemContent(flourInventory, FLOUR, FLOUR_QUANTITY);
    }

    @Test
    public void testAddInventoryItemsToDatabase() {
        InventoryItem sugarInventory = InventoryItem.create(SUGAR, SUGAR_QUANTITY);
        InventoryItem flourInventory = InventoryItem.create(FLOUR, FLOUR_QUANTITY);

        // add items to the database
        daoInventory.addInventoryItem(sugarInventory);
        daoInventory.addInventoryItem(flourInventory);

        List<InventoryItem> inventoryItems = daoInventory.getInventoryItems();
        // confirm the number of items in the database
        assertEquals(inventoryItems.size(), 2);
        // and the contents
        testInventoryItemContent(inventoryItems.get(0), SUGAR, SUGAR_QUANTITY);
        testInventoryItemContent(inventoryItems.get(1), FLOUR, FLOUR_QUANTITY);
    }

    private void testInventoryItemContent(InventoryItem item, String expectedName, double expectedQuantity) {
        assertEquals(item.getName(), expectedName);
        assertEquals(item.getQuantity(), expectedQuantity, 0);
    }
}
