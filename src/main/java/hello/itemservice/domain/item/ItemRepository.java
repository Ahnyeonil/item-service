package hello.itemservice.domain.item;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Repository
public class ItemRepository {

    private static final Map<Long, Item> store = new ConcurrentHashMap<>();
    private static long sequance = 0L;

    public Item save(Item item) {
        item.setId(++sequance);
        store.put(item.getId(), item);
        return item;
    }

    public Item findById(Long id) {
        return store.get(id);
    }

    public List<Item> findAll() {
        return new ArrayList<>(store.values());
    }

    public void update(Long itemId, Item updateParam) {
        Item findItem = findById(itemId);
        findItem.setItemName(updateParam.getItemName());
        findItem.setPrice(updateParam.getPrice());
        findItem.setQuantity(updateParam.getQuantity());
    }

    public void delete(Long itemId) {
        store.remove(itemId);
    }

    public void clearStore() {
        store.clear();
    }
}
