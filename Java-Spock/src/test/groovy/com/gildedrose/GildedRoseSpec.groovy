package com.gildedrose

import spock.lang.Specification


class GildedRoseSpec extends Specification {

    def "common item workflow"() {

        given: "an item"
        Item[] items = [new Item("anItem", 5, 4)]

        and: "the application with these items"
        GildedRose app = new GildedRose(items)

        when: "updating quality"
        app.updateQuality()

        then: "the quality is correct"
        app.items[0].name == "anItem"
        app.items[0].quality == 3
        app.items[0].sellIn == 4
    }


    def "aged brie workflow"() {

        given: "an item"
        Item[] items = [new Item("Aged Brie", 5, 4)]

        and: "the application with these items"
        GildedRose app = new GildedRose(items)

        when: "updating quality"
        app.updateQuality()

        then: "the quality is correct"
        app.items[0].name == "Aged Brie"
        app.items[0].quality == 5
        app.items[0].sellIn == 4
    }

    def "quality can not be negative"() {
        given: "an item"
        Item[] items = [new Item("anItem", 5, 0)]

        and: "the application with these items"
        GildedRose app = new GildedRose(items)

        when: "updating quality"
        app.updateQuality()

        then: "the quality is correct"
        app.items[0].name == "anItem"
        app.items[0].quality == 0
        app.items[0].sellIn == 4
    }

    def "quality can not be higher than 50"() {
        given: "Aged Brie"
        Item[] items = [new Item("Aged Brie", 5, 50)]

        and: "the application with these items"
        GildedRose app = new GildedRose(items)

        when: "updating quality"
        app.updateQuality()

        then: "the quality is correct"
        app.items[0].name == "Aged Brie"
        app.items[0].quality == 50
        app.items[0].sellIn == 4
    }

    def "Sulfuras, Hand of Ragnaros is not reducing quality or sellin"() {
        given: "Sulfuras, Hand of Ragnaros"
        Item[] items = [new Item("Sulfuras, Hand of Ragnaros", 45, 80)]

        and: "the application with these items"
        GildedRose app = new GildedRose(items)

        when: "updating quality"
        app.updateQuality()

        then: "the quality is correct"
        app.items[0].name == "Sulfuras, Hand of Ragnaros"
        app.items[0].quality == 80
        app.items[0].sellIn == 45
    }

    def "negative sellin results to twice as fast quality loss"() {
        given: "an item"
        Item[] items = [new Item("an item", -10, 20)]

        and: "the application with these items"
        GildedRose app = new GildedRose(items)

        when: "updating quality"
        app.updateQuality()

        then: "the quality is correct"
        app.items[0].name == "an item"
        app.items[0].quality == 18
        app.items[0].sellIn == -11
    }

    def "backstage passes more than 10 days from the concert" () {
        given: "Backstage passes to a TAFKAL80ETC concert"
        Item[] items = [new Item("Backstage passes to a TAFKAL80ETC concert", 20, 20)]

        and: "the application with these items"
        GildedRose app = new GildedRose(items)

        when: "updating quality"
        app.updateQuality()

        then: "the quality is correct"
        app.items[0].name == "Backstage passes to a TAFKAL80ETC concert"
        app.items[0].quality == 21
        app.items[0].sellIn == 19
    }


    def "backstage passes 10 days from the concert" () {
        given: "Backstage passes to a TAFKAL80ETC concert"
        Item[] items = [new Item("Backstage passes to a TAFKAL80ETC concert", 10, 20)]

        and: "the application with these items"
        GildedRose app = new GildedRose(items)

        when: "updating quality"
        app.updateQuality()

        then: "the quality is correct"
        app.items[0].name == "Backstage passes to a TAFKAL80ETC concert"
        app.items[0].quality == 22
        app.items[0].sellIn == 9
    }

    def "backstage passes 5 days from the concert" () {
        given: "Backstage passes to a TAFKAL80ETC concert"
        Item[] items = [new Item("Backstage passes to a TAFKAL80ETC concert", 5, 20)]

        and: "the application with these items"
        GildedRose app = new GildedRose(items)

        when: "updating quality"
        app.updateQuality()

        then: "the quality is correct"
        app.items[0].name == "Backstage passes to a TAFKAL80ETC concert"
        app.items[0].quality == 23
        app.items[0].sellIn == 4
    }

    def "backstage passes after the concert" () {
        given: "Backstage passes to a TAFKAL80ETC concert"
        Item[] items = [new Item("Backstage passes to a TAFKAL80ETC concert", -4, 20)]

        and: "the application with these items"
        GildedRose app = new GildedRose(items)

        when: "updating quality"
        app.updateQuality()

        then: "the quality is correct"
        app.items[0].name == "Backstage passes to a TAFKAL80ETC concert"
        app.items[0].quality == 0
        app.items[0].sellIn == -5
    }

    def "Conjured items update quality"() {
        given: "a conjured item"
        Item[] items = [new Item("Conjured", 7, 20)]

        and: "the application with these items"
        GildedRose app = new GildedRose(items)

        when: "updating quality"
        app.updateQuality()

        then: "the quality is correct"
        app.items[0].name == "Conjured"
        app.items[0].quality == 18
        app.items[0].sellIn == 6
    }
}
