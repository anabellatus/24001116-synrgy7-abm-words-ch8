package com.anabell.words.data.datasource.remote

import com.anabell.words.data.GadgetRemoteDataSource
import com.anabell.words.data.datasource.remote.retrofit.GadgetService
import com.anabell.words.data.model.CategoryGadget
import com.anabell.words.data.model.Gadget

class GadgetRemoteDataSourceImpl (
    private val gadgetService: GadgetService
): GadgetRemoteDataSource {
    override suspend fun fetchGadgetData(): List<Gadget> {
        return gadgetService.getGadgets()
//        listOf(
//            Gadget(
//                id = 1,
//                image = "https://images.samsung.com/is/image/samsung/assets/id/2401/home/HOME_Q5_Merchandising_160x160_pc.png?\$160_160_PNG\$",
//                name = "Samsung Galaxy Z Fold5",
//                category = "Smartphone",
//                price = 23499000
//            ),
//            Gadget(
//                id = 2,
//                image = "https://us.soundcore.com/cdn/shop/files/1_c8beed20-08a1-4e78-ba12-f1d042bbf123_1200x.jpg?v=1712643980",
//                name = "Soundcore Motion 300",
//                category = "Speaker",
//                price = 1040000
//            ),
//            Gadget(
//                id = 3,
//                image = "https://images.samsung.com/is/image/samsung/p6pim/id/mx-st40b-xd/gallery/id-mx-st40b-mx-st40b-xd-thumb-536315186?\$252_252_PNG\$",
//                name = "Samsung Sound Tower MX-ST40B",
//                category = "Speaker",
//                price = 4990000
//            ),
//            Gadget(
//                id = 4,
//                image = "https://id.jbl.com/dw/image/v2/AAUJ_PRD/on/demandware.static/-/Sites-masterCatalog_Harman/default/dw5115a3ac/1_JBL_PULSE_5_HERO_34364_x2.jpg?sw=270&sh=330&sm=fit&sfrm=png",
//                name = "JBL Pulse 5",
//                category = "Speaker",
//                price = 4790000
//            ),
//            Gadget(
//                id = 5,
//                image = "https://images.tokopedia.net/img/JFrBQq/2023/6/28/4fc8aa7c-a26a-49b6-bcff-965cd1abbd13.jpg",
//                name = "ROG Alley",
//                category = "Game Console",
//                price = 19249000
//            ),
//            Gadget(
//                id = 6,
//                image = "https://assets.nintendo.com/image/upload/f_auto/q_auto/dpr_1.5/c_scale,w_300/ncom/en_US/switch/site-design-update/HEGS_001_heroWA_07_R_ad-0-OLED-V4",
//                name = "Nintendo Switch Oled",
//                category = "Game Console",
//                price = 5671000
//            ),
//            Gadget(
//                id = 7,
//                image = "https://assets.nintendo.com/image/upload/f_auto/q_auto/dpr_1.5/c_scale,w_300/ncom/en_US/switch/refresh/switchindock",
//                name = "Nintendo Switch",
//                category = "Game Console",
//                price = 4895000
//            ),
//            Gadget(
//                id = 8,
//                image = "https://www.apple.com/v/iphone/home/bu/images/overview/select/iphone_14__cjgvgyn9cquu_large.png",
//                name = "Iphone 14",
//                category = "Smartphone",
//                price = 12249000
//            ),
//            Gadget(
//                id = 9,
//                image = "https://images.samsung.com/is/image/samsung/assets/id/offer/2024/3/home/galaxy-a05s-light-violet.png?\$160_160_PNG\$",
//                name = "Samsung Galaxy A05s",
//                category = "Smartphone",
//                price = 1924900
//            ),
//            Gadget(
//                id = 10,
//                image = "https://www.apple.com/v/iphone/home/bu/images/overview/select/iphone_15_pro__bpnjhcrxofqu_large.png",
//                name = "Iphone 15 Pro",
//                category = "Smartphone",
//                price = 18249000
//            ),
//            Gadget(
//                id = 11,
//                image = "https://us.soundcore.com/cdn/shop/products/A3939011_NoiseCancellingEarbuds_TD01_1200x.jpg?v=1650016047",
//                name = "Soundcore Life P3",
//                category = "Earphone",
//                price = 1024900
//            ),
//            Gadget(
//                id = 12,
//                image = "https://i02.appmifile.com/240_item_id/18/02/2024/f85edac6ab911f62f35fe63f7f9388a7.png?thumb=1&w=500&f=webp&q=85",
//                name = "Xiamomi Buds 5 Pro",
//                category = "Earphone",
//                price = 774000
//            ),
//            Gadget(
//                id = 13,
//                image = "https://i01.appmifile.com/v1/MI_18455B3E4DA706226CF7535A58E875F0267/pms_1678788817.65751206.png?thumb=1&w=500&f=webp&q=85",
//                name = "Xiamomi Buds 4",
//                category = "Earphone",
//                price = 399000
//            ),
//            Gadget(
//                id = 14,
//                image = "https://images.samsung.com/is/image/samsung/assets/id/2208/pcd/watches/Visual_LNB_Fit3_PC.png",
//                name = "Galaxy Fit3",
//                category = "Smartwatch",
//                price = 4499000
//            ),
//            Gadget(
//                id = 15,
//                image = "https://i02.appmifile.com/998_item_id/19/02/2024/f9b4ff0d1564f41fe67c79d66b973056.png?thumb=1&w=500&f=webp&q=85",
//                name = "Redmi Note 13 Pro 5G",
//                category = "Smartphone",
//                price = 4399000
//            ),
//            Gadget(
//                id = 16,
//                image = "https://cdn.eraspace.com/media/catalog/product/a/p/apple_watch_ultra_2_49mm_gps_cellular_titanium_case_with_orange_ocean_band_1_1.jpg",
//                name = "Apple Watch Ultra 2",
//                category = "Smartwatch",
//                price = 15099000
//            ),
//            Gadget(
//                id = 17,
//                image = "https://cdn.eraspace.com/media/catalog/product/a/p/apple_watch_series_9_45mm_gps_silver_aluminium_case_with_winter_blue_sport_loop_1_2.jpg",
//                name = "Apple Watch Series 9",
//                category = "Smartwatch",
//                price = 5099000
//            ),
//            Gadget(
//                id = 18,
//                image = "https://i02.appmifile.com/781_item_id/18/02/2024/5df2f5a9c98e706fe1a88bd42ae6259c.png?thumb=1&w=500&f=webp&q=85",
//                name = "Redmi Watch 4",
//                category = "Smartwatch",
//                price = 1174000
//            ),
//            Gadget(
//                id = 19,
//                image = "https://p2-ofp.static.pub/fes/cms/2022/11/04/w9qxwpixgvyjsk8dtlfrb8y0r42ucz043299.png",
//                name = "Lenovo ThinkPad X1 Carbon Gen 11",
//                category = "Laptop",
//                price = 19099000
//            ),
//            Gadget(
//                id = 20,
//                image = "https://p4-ofp.static.pub//fes/cms/2023/12/14/hnmqd94mbj56ns633uq9w4i3ol508d529200.png",
//                name = "Lenovo Yoga Pro 9i",
//                category = "Laptop",
//                price = 33000000
//            ),
//            Gadget(
//                id = 21,
//                image = "https://images.samsung.com/is/image/samsung/assets/us/galaxybooks/03252024/GB_PCD_FT12-3_GB4-pro_PC.jpg",
//                name = "Galaxy Book 4 Pro",
//                category = "Laptop",
//                price = 13500000
//            ),
//            Gadget(
//                id = 22,
//                image = "https://images.samsung.com/is/image/samsung/assets/us/galaxybooks/03262024/GB_PCD_FT12_GB4Pro360_PC-V2.jpg?",
//                name = "Galaxy Book 4 Pro 360",
//                category = "Laptop",
//                price = 25000000
//            ),
//            Gadget(
//                id = 23,
//                image = "https://p2-ofp.static.pub//fes/cms/2024/02/13/5pi4rlc0jxroopjbnijh5071i67e7a018268.png",
//                name = "Lenovo Legion 5i",
//                category = "Laptop",
//                price = 28000000
//            ),
//            Gadget(
//                id = 24,
//                image = "https://images.samsung.com/is/image/samsung/p6pim/id/sm-x216bdbexid/gallery/id-galaxy-tab-a9-plus-sm-x216-sm-x216bdbexid-thumb-538770339?\$252_252_PNG\$",
//                name = "Galaxy Tab A9+",
//                category = "Tablet",
//                price = 3499000
//            ),
//            Gadget(
//                id = 25,
//                image = "https://images.samsung.com/is/image/samsung/p6pim/id/sm-x516bzaaxid/gallery/id-galaxy-tab-s9-fe-sm-x516-sm-x516bzaaxid-thumb-538495222?\$172_172_PNG\$",
//                name = "Galaxy Tab S9 FE",
//                category = "Tablet",
//                price = 7499000
//            ),
//            Gadget(
//                id = 26,
//                image = "https://cdn.eraspace.com/media/catalog/product/i/p/ipad_pro_gen_4_11_inci_wi-fi_space_grey_1_6.jpg",
//                name = "Ipad Pro Gen6",
//                category = "Tablet",
//                price = 15499000
//            ),
//            Gadget(
//                id = 27,
//                image = "https://cdn.eraspace.com/media/catalog/product/i/p/ipad_air_gen_5_10_9_inci_wi-fi_purple_2_4.jpg",
//                name = "Ipad Air Gen5",
//                category = "Tablet",
//                price = 9749000
//            ),
//            Gadget(
//                id = 28,
//                image = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQG2jz7zHHuupmOXtIhMUzhLKvVhi0ZDAvzHUvKtFa4Yw&s",
//                name = " Xiaomi Smart Camera C300",
//                category = "Camera",
//                price = 399000
//            ),
//            Gadget(
//                id = 29,
//                image = "https://id.canon/media/image/2023/02/06/c83adfc2fcaf473f9935b6962f114b45_EOS+R50+Black+RF-S18-45mm.png",
//                name = "Canon EOS R50",
//                category = "Camera",
//                price = 11999000
//            ),
//            Gadget(
//                id = 30,
//                image = "https://instaxshop.co.id/cdn/shop/products/go3_540x.jpg?v=1629348593",
//                name = "INSTA360 GO 2 Mini Action Camera",
//                category = "Camera",
//                price = 4899000
//            ),
//            Gadget(
//                id = 31,
//                image = "https://i02.appmifile.com/105_item_id/17/10/2023/e475e86487d48b7b29f3e288a571abeb.png?width=200&height=200",
//                name = "Official Xiaomi Google TV A",
//                category = "TV",
//                price = 1899000
//            ),
//            Gadget(
//                id = 32,
//                image = "https://i02.appmifile.com/358_item_id/10/04/2024/d6cd696fbcb11f7fc82c76407b482f20.png?thumb=1&w=500&f=webp&q=85",
//                name = "Official Xiaomi TV A Pro",
//                category = "TV",
//                price = 1899000
//            ),
//            Gadget(
//                id = 33,
//                image = "https://images.samsung.com/is/image/samsung/p6pim/id/f-id65cu7000u5/gallery/id-65-inch-crystal-uhd-4k-cu7000-and-hw-c450-soundbar-f-id65cu7000u5-thumb-537208664?",
//                name = "Samsung Crystal UHD",
//                category = "TV",
//                price = 1899000
//            ),
//            Gadget(
//                id = 34,
//                image = "https://images.philips.com/is/image/philipsconsumer/71571f3469244fafaaa5add001272fde",
//                name = "Philips 1000 Series Electric Tooth Brush",
//                category = "Tools",
//                price = 549900
//            ),
//            Gadget(
//                id = 35,
//                image = "https://i02.appmifile.com/735_item_id/31/08/2023/419e57f3bbea2b63c9b9b5fb6dbaa5d1.png?thumb=1&w=500&f=webp&q=85",
//                name = "Xiaomi Compact Hair Dryer H101",
//                category = "Tools",
//                price = 249000
//            ),
//            Gadget(
//                id = 36,
//                image = "https://i02.appmifile.com/87_item_id/17/11/2023/686b5063e239f844a30694c20c604c67.png?thumb=1&w=500&f=webp&q=85",
//                name = "Xiaomi Electric Shaver S101",
//                category = "Tools",
//                price = 249000
//            ),
//        )
    }

    override suspend fun fetchGadgetCategoryData(): List<CategoryGadget> {
        return gadgetService.getCategories()
//        listOf(
//            CategoryGadget(
//                icon = "https://cdn-icons-png.flaticon.com/512/3437/3437364.png",
//                name = "Smartphone",
//            ),
//            CategoryGadget(
//                icon = "https://cdn-icons-png.flaticon.com/512/2888/2888704.png",
//                name = "Laptop",
//            ),
//            CategoryGadget(
//                icon = "https://cdn-icons-png.flaticon.com/512/564/564579.png",
//                name = "Tablet",
//            ),
//            CategoryGadget(
//                icon = "https://cdn-icons-png.flaticon.com/512/5906/5906114.png",
//                name = "Earphone",
//            ),
//            CategoryGadget(
//                icon = "https://cdn-icons-png.flaticon.com/512/2668/2668914.png",
//                name = "Camera",
//            ),
//            CategoryGadget(
//                icon = "https://cdn-icons-png.flaticon.com/512/8462/8462356.png",
//                name = "Speaker",
//            ),
//            CategoryGadget(
//                icon = "https://cdn-icons-png.flaticon.com/512/617/617694.png",
//                name = "Smartwatch",
//            ),
//            CategoryGadget(
//                icon = "https://cdn-icons-png.flaticon.com/512/771/771261.png",
//                name = "Game Console",
//            ),
//            CategoryGadget(
//                icon = "https://cdn-icons-png.flaticon.com/512/5606/5606227.png",
//                name = "TV",
//            ),
//            CategoryGadget(
//                icon = "https://cdn-icons-png.flaticon.com/512/6212/6212141.png",
//                name = "Tools",
//            ),
//        )
    }
}