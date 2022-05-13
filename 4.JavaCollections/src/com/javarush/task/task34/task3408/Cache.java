package com.javarush.task.task34.task3408;

//import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.WeakHashMap;

public class Cache<K, V> {
    private Map<K, V> cache = new WeakHashMap<>();   //add your code here

    public V getByKey(K key, Class<V> clazz) throws Exception {
        // add your code here
        V obj = cache.get(key);
        if (obj == null) {
            obj = clazz.getConstructor(key.getClass()).newInstance(key);
            cache.put(key, obj);
        }
        return obj;
    }

    public boolean put(V obj) {
        // add your code here
        try {
            Method m = obj.getClass().getDeclaredMethod("getKey");
            m.setAccessible(true);
            K key = (K) m.invoke(obj);
            cache.put(key, obj);
            return true;
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            return false;
        }
    }

    public int size() {
        return cache.size();
    }
}
