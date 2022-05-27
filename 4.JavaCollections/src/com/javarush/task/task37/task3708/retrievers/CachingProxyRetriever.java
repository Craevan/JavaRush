package com.javarush.task.task37.task3708.retrievers;

import com.javarush.task.task37.task3708.cache.LRUCache;
import com.javarush.task.task37.task3708.storage.Storage;

public class CachingProxyRetriever implements Retriever {

    private final OriginalRetriever originalRetriever;
    private final LRUCache<Long, Object> cache = new LRUCache<>(10);

    public CachingProxyRetriever(Storage storage) {
        this.originalRetriever = new OriginalRetriever(storage);
    }

    @Override
    public Object retrieve(long id) {
        if (cache.find(id) != null) {
            return cache.find(id);
        }
        else {
            Object o = originalRetriever.retrieve(id);
            cache.set(id, o);
            return o;
        }
    }
}
