package com.javarush.task.task33.task3310.strategy;

import java.io.IOException;

public class FileStorageStrategy implements StorageStrategy {

    static final int DEFAULT_INITIAL_CAPACITY = 16;
    static final long DEFAULT_BUCKET_SIZE_LIMIT = 10000;

    FileBucket[] table;
    int size;
    private long bucketSizeLimit = DEFAULT_BUCKET_SIZE_LIMIT;
    long maxBucketSize;

    public FileStorageStrategy() {
        try {
            init();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void init() throws IOException {
        table = new FileBucket[DEFAULT_INITIAL_CAPACITY];
        for (int i = 0; i < table.length; i++) {
            table[i] = new FileBucket();
        }
    }

    static int indexFor(int hash, int length) {
        return hash & (length - 1);
    }

    final Entry getEntry(Long key) throws IOException, ClassNotFoundException {
        if (size == 0) {
            return null;
        }

        int index = indexFor(key.hashCode(), table.length);
        for (Entry entry = table[index].getEntry(); entry != null; entry = entry.next) {
            if (key.equals(entry.key)) {
                return entry;
            }
        }
        return null;
    }

    public void put(Long key, String value) {
        int hash = key.hashCode();
        int index = indexFor(hash, table.length);
        try {
            for (Entry e = table[index].getEntry(); e != null; e = e.next) {
                if (key.equals(e.key)) {
                    e.value = value;
                    return;
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        addEntry(hash, key, value, index);
    }

    void resize(int newCapacity) throws IOException, ClassNotFoundException {
        FileBucket[] newTable = new FileBucket[newCapacity];

        for (int i = 0; i < newTable.length; i++)
            newTable[i] = new FileBucket();

        transfer(newTable);

        for (FileBucket fileBucket : table) fileBucket.remove();

        table = newTable;
    }

    void transfer(FileBucket[] newTable) throws IOException, ClassNotFoundException {
        int newCapacity = newTable.length;
        maxBucketSize = 0;

        for (FileBucket fileBucket : table) {
            Entry entry = fileBucket.getEntry();
            while (entry != null) {
                Entry next = entry.next;
                int indexInNewTable = indexFor(entry.getKey().hashCode(), newCapacity);
                entry.next = newTable[indexInNewTable].getEntry();
                newTable[indexInNewTable].putEntry(entry);
                entry = next;
            }

            long currentBucketSize = fileBucket.getFileSize();
            if (currentBucketSize > maxBucketSize)
                maxBucketSize = currentBucketSize;
        }
    }

    public boolean containsValue(String value) {
        for (FileBucket tableElement : table) {
            try {
                for (Entry e = tableElement.getEntry(); e != null; e = e.next)
                    if (value.equals(e.value))
                        return true;
            } catch (IOException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        return false;
    }

    public String getValue(Long key) {
        Entry entry;
        try {
            entry = getEntry(key);
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        if (entry != null)
            return entry.getValue();

        return null;
    }

    public boolean containsKey(Long key) {
        try {
            return getEntry(key) != null;
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public Long getKey(String value) {
        for (FileBucket tableElement : table) {
            try {
                for (Entry e = tableElement.getEntry(); e != null; e = e.next)
                    if (value.equals(e.value))
                        return e.getKey();
            } catch (IOException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        return null;
    }

    void addEntry(int hash, Long key, String value, int bucketIndex) {
        if ((maxBucketSize > bucketSizeLimit)) {
            try {
                resize(2 * table.length);
            } catch (IOException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
            bucketIndex = indexFor(key.hashCode(), table.length);
        }

        createEntry(hash, key, value, bucketIndex);
    }

    void createEntry(int hash, Long key, String value, int bucketIndex) {
        Entry e;
        try {
            e = table[bucketIndex].getEntry();
        } catch (IOException | ClassNotFoundException ex) {
            throw new RuntimeException(ex);
        }
        try {
            table[bucketIndex].putEntry(new Entry(hash, key, value, e));
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        size++;

        long currentBucketSize;
        try {
            currentBucketSize = table[bucketIndex].getFileSize();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        if (currentBucketSize > maxBucketSize)
            maxBucketSize = currentBucketSize;
    }

    public long getBucketSizeLimit() {
        return bucketSizeLimit;
    }

    public void setBucketSizeLimit(long bucketSizeLimit) {
        this.bucketSizeLimit = bucketSizeLimit;
    }
}

