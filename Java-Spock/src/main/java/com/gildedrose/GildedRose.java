package com.gildedrose;

class GildedRose {
    private static final String BACKSTAGE_PASSES_TO_A_TAFKAL_80_ETC_CONCERT = "Backstage passes to a TAFKAL80ETC concert";
    private static final int MAXIMUM_QUALITY_VALUE = 50;
    private static final String AGED_BRIE = "Aged Brie";
    private static final String SULFURAS_HAND_OF_RAGNAROS = "Sulfuras, Hand of Ragnaros";
    private static final int BACKSTAGE_PASSES_FIRST_TIER = 10;
    private static final int BACKSTAGE_PASSES_SECOND_TIER = 5;
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (Item item : items) {
            qualityCalculation(item);
            sellInCalculation(item);
        }
    }

    private void sellInCalculation(Item item) {
        if (!item.name.equals(SULFURAS_HAND_OF_RAGNAROS)) {
            item.sellIn = item.sellIn - 1;
        }

        if (item.sellIn < 0) {
            handleNegativeSellIn(item);
        }
    }

    private void qualityCalculation(Item item) {
        if (isNotSpecialItem(item)) {
            if (item.quality > 0) {
                item.quality = item.quality - 1;
            }
        } else {
            specialItemsQualityCalculation(item);
        }
    }

    private void specialItemsQualityCalculation(Item item) {
        if (item.quality < MAXIMUM_QUALITY_VALUE) {
            item.quality = item.quality + 1;

            if (item.name.equals(BACKSTAGE_PASSES_TO_A_TAFKAL_80_ETC_CONCERT)) {
                if (item.sellIn <= BACKSTAGE_PASSES_FIRST_TIER) {
                    if (item.quality < MAXIMUM_QUALITY_VALUE) {
                        item.quality = item.quality + 1;
                    }
                }

                if (item.sellIn <= BACKSTAGE_PASSES_SECOND_TIER) {
                    if (item.quality < MAXIMUM_QUALITY_VALUE) {
                        item.quality = item.quality + 1;
                    }
                }
            }
        }
    }

    private void handleNegativeSellIn(Item item) {
        if (item.name.equals(SULFURAS_HAND_OF_RAGNAROS)) {
            return;
        } else if (item.name.equals(AGED_BRIE)) {
            if (item.quality < MAXIMUM_QUALITY_VALUE) {
                item.quality = item.quality + 1;
            }
        } else if (item.name.equals(BACKSTAGE_PASSES_TO_A_TAFKAL_80_ETC_CONCERT)) {
            item.quality = 0;
        } else {
            if (item.quality > 0) {
                item.quality = item.quality - 1;
            }

        }
    }

    private boolean isNotSpecialItem(Item item) {
        return !item.name.equals(AGED_BRIE)
                && !item.name.equals(BACKSTAGE_PASSES_TO_A_TAFKAL_80_ETC_CONCERT)
                && !item.name.equals(SULFURAS_HAND_OF_RAGNAROS);
    }
}
