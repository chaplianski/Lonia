package com.example.testtask.data.storage.database

import com.example.testtask.data.storage.storagies.PortStorage
import javax.inject.Inject

class PortStorageImpl @Inject constructor() : PortStorage {

    override fun getPortList(): List<String> {
        return listOf(
            "AT SEA",
            "Jebel Ali",
            "LeHavre",
            "Oita",
            "Venice",
            "Shenzhen",
            "Huangpu",
            "Mailiao",
            "Burgas",
            "Thessaloniki",
            "Melaka",
            "Agioi Theodoroi",
            "Cartagena",
            "Mailao",
            "From Malta to Cartagena",
            "Antwerp",
            "S. Panagia",
            "Augusta",
            "Fujairah",
            "Ghent",
            "Tangjung Lasat",
            "Rotterdam",
            "Chiba",
            "Singapore",
            "Spore",
            "Aghioi Theodoroi",
            "Huelva",
            "Lagos",
            "Malta",
            "Kostanza",
            "Incheon",
            "Yosu",
            "Off Southwold",
            "Lavera",
            "Ulsan",
            "Alexandria",
            "Sarroch",
            "Mailia",
            "Ceyhan",
            "Lagos (anchorage)",
            "Huizhu",
            "Daesan",
            "Costantza",
            "KOUROS",
            "Singapore, AT SEA",
            "Novorossiysk",
            "Sao Sebastiao",
            "Qingdao",
            "Bahrain",
            "Malta (OPL)",
            "Skikda",
            "From Piraeus To Malta",
            "Off Malta",
            "Koper",
            "Long Beach -Kalifornia",
            "Mersin",
            "Antalya",
            "Prindisi",
            "Freeport",
            "Cotonou",
            "Suez",
            "Sete",
            "Savannah",
            "JEBEL ALI",
            "Constanta",
            "Constantza",
            "Elefsina",
            "Fiumicino",
            "Barcelona",
            "Piraeus-Omisalj-Venica",
            "Agioi Theodoroi,Bourgas",
            "La Pallice",
            "Sohar to Fujairah",
            "Lingue",
            "KOPPER - SUEZ",
            "Cotonou-Lagos",
            "AT SEA FROM GIBRALTAR TO NEW YORK",
            "Augusta, Italy",
            "Sakai, Japan",
            "Thessaloniki, Greece",
            "Jacksonville, FL",
            "PALDISKI, ESTONIA",
            "COTONOU",
            "VALENCIA",
            "Augusta - Italy",
            "Ashkelon, Israel",
            "Aliaga",
            "Cyprus, offshore, STS",
            "Port Arthur, Texas",
            "Yeosu",
            "Santa Panagia, Italy",
            "Richmond, CA USA",
            "Jebel Ali, Tanker Berth #1, UAE",
            "Civitavecchia, Italy",
            "Lagos, Anchorage.",
            "AT SEA FRO SINGAPORE TO YEOSU",
            "AT SEA FROM FUJAIRAH TO KUWAIT",
            "SUEZ - ROTTERDAM",
            "Askelon",
            "Bin Qasim",
            "Thessaloniki to Malta OPL",
            "OPL MALTA",
            "Tarragona",
            "Ag. Theodoroi",
            "Dickson",
            "Bayonne, NJ, USA",
            "Lome / Togo",
            "Piraeus",
            "Antwerp to Baton Rouge",
            "JEBEL ALI TO KARACHI",
            "Cotonou  to Lome",
            "Lome  to P. Harcourt",
            "Augusta - Tunnis",
            "ANTWEP",
            "Ashkelon - Naples",
            "OPL Malta to Venice",
            "MILLAZO",
            "Las Palmas to Rotterdam",
            "Zarate",
            "LAGOS",
            "Gibraltar",
            "Abidjan",
            "Aden",
            "Santa Panagia",
            "Port Qasim",
            "Sikka",
            "Bilbao",
            "Karachi",
            "Port Sikka",
            "Vopac Banyan Jetty #5",
            "Baton Rouge, TX",
            "New Orleans to Baton Rouge LA",
            "Chiba / Idemitsu No. 1 pier",
            "Ras Tanura",
            "Europort",
            "Porto Marghera",
            "Iskenderun",
            "Jorf Lasfar",
            "Ashkelon",
            "Laskhra",
            "From Ventspls to Skagen",
            "From: Singapore To: Kaohsiung, Taiwan",
            "Fm: Pascagoula, U.S To: Freeport, Bahamas",
            "MALTA",
            "Brindisi",
            "La Skhira",
            "Malta-Brindisi",
            "From Malta to La Shkira",
            "AMA anchorage, Louisiana",
            "LAGOS ANCHORAGE",
            "Tutunciftlik",
            "Sea",
            "Limassol",
            "NEW YORK",
            "IMTT-Bayonne, New Jersey",
            "St.Panagia",
            "Houston",
            "Primorsk",
            "Fawley",
            "Amsterdam",
            "Thames Haven",
            "Teesport",
            "At Lagos",
            "From: Ventspils To: Amsterdam",
            "From: Riga To: Teesport,Uk",
            "at Thames Haven, UK",
            "Ventspills",
            "VENTSPILS",
            "Braintree, Massachusetts",
            "St. Panagia",
            "Port Everglades",
            "Slagen",
            "Galveston",
            "Houston, Texas",
            "Jamnagar terminal, Sikka",
            "LAS PALMAS",
            "Bizerte",
            "Tanjung Pelepas",
            "Halifax",
            "Ereglisi",
            "KARACHI",
            "TANJUNG PELEPAS",
            "St Charles",
            "Tunis",
            "STS off Lome",
            "Marmara Ereglisi",
            "CORPUS CHRISTI",
            "Pajaritos",
            "Aruba Anchorage",
            "Pasir Gudang",
            "Algeciras",
            "Long Beach, LA",
            "Aruba",
            "Long Beach",
            "KOPER",
            "MAILIAO",
            "Lome",
            "SLAGEN",
            "New York",
            "SARROCH",
            "Anyer",
            "LA",
            "AMS TO LASPALMAS",
            "Chittagong",
            "ADABIYA TO ASPROPYRGOS",
            "Reading, NJ",
            "Baytown",
            "El Segundo",
            "Boston",
            "Ventspils",
            "Providence",
            "Corpus Christi, Texas",
            "Dunkerque",
            "Corpus Christi",
            "Bourgas",
            "P Marghera",
            "Lake Charles",
            "DAESAN",
            "La Porte",
            "Itaqui Terminal",
            "Guayanilla",
            "Houizhou",
            "Huizhou",
            "Tuapse",
            "Taipei",
            "Taichung",
            "Mombasa",
            "SUEZ-ALGECIRAS",
            "New Orlean",
            "New Orleans",
            "Hamburg",
            "Pembroke to Boston",
            "Ennore",
            "Algiers",
            "Balboa to Everglades",
            "OAKLAND",
            "Richmond",
            "Garyville",
            "FOS",
            "Singapore - Yeosu",
            "Fos",
            "Map Ta Phut",
            "Genoa",
            "Kandla",
            "San Francisco",
            "Port Aurthur",
            "Los Angeles",
            "Aqaba",
            "Derince",
            "Durban",
            "Las Palmas",
            "OPL Malta",
            "Malta OPL",
            "SINGAPORE_ULSAN",
            "Philadelphia, PA",
            "Cristobal to Come by chance",
            "Immingham",
            "Philadelphia",
            "Qubec",
            "Quebec",
            "Kalundborg",
            "Rosarito",
            "Rosaritos",
            "HUELVA",
            "Carteret",
            "Port Arthur",
            "Tanjung Uban",
            "OPL Limassol",
            "PRIMORSK",
            "Trieste",
            "Cherry point Usa",
            "Trabzon",
            "Jose Terminal",
            "Askhelon",
            "Taranto",
            "Kaohsiung",
            "Civitavecchia",
            "Maputo",
            "Mundra",
            "Balboa to San Francisco",
            "Colombo",
            "Saint Croix",
            "Antwerpen",
            "Balboa",
            "El Dekheila",
            "Vysotsk",
            "AMSTERDAM",
            "Afrika Haven",
            "Port Of New York And New Jersey",
            "Port Hedland",
            "Hedland",
            "Wilhelmshaven",
            "Maliao",
            "St.Croix",
            "Fujeirah Anchorage",
            "SUEZ",
            "Salvador",
            "Sika",
            "Campana",
            "Brisbane",
            "Syros - Piraeus",
            "Las Palmas - Mongstad",
            "Terneuzen",
            "Callao",
            "Marsaxlokk",
            "New Jersey",
            "Goteborg",
            "Skagen - Gothenburg",
            "Melones Oil Terminal",
            "Ciudad Madero",
            "Las Palmas to Roterdam",
            "GIBRALTAR OPL-ANTWERP",
            "ROTTERDAM",
            "Tracy",
            "Dakar"
        )
    }
}