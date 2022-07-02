package com.example.lonia.presenter.ui.helpers

import com.example.lonia.domain.model.Questions

class CategoryFilterHelper {

    companion object {

        fun getQuestionsAndCategory(questions: List<Questions>): Map<String, List<Questions>> {
            val listQuestions = questions as MutableList<Questions>
            val questionsMap = mutableMapOf<String, List<Questions>>()

            for (question in listQuestions) {
                val category = when (question.categoryid) {
                    "1859", "1860", "1861", "1862", "1863", "1864", "1865", "1866", "1867", "1868", "1869", "1870", "1871" -> "Certification and Documentation"
                    "1872", "1873", "1874", "1875", "1876", "1877", "1878", "1879", "1880", "1881", "1882", "1883", "1884", "1885", "1886", "1888", "1889", "1890" -> "Crew managment"
                    "1887" -> "General appearance and condition"
                    "1891" -> "Fire fighting Equipment"
                    "1892" -> "Access"
                    "1893", "1894", "1896" -> "Pollution prevention"
                    "1895" -> "Pilotage"
                    "1897" -> "Pump Rooms and Oil Discharge Monitors"
                    "1898" -> "Pump Rooms and Oil Discharge Monitors"
                    "1899", "1900" -> "Engine and steering compartments"
                    "1901" -> "Maritime Security"
                    "1902" -> "Policies and Procedures"
                    "1903" -> "Cargo and Ballast Systems - Petroleum"
                    "1904", "1968", "2115" -> "Policies, Procedures and Documentation"
                    "1905" -> "Stability and Cargo Loading Limitations"
                    "1906", "1907", "1908" -> "Cargo Operations and Related Safety Management"
                    "1909" -> "Venting Arrangements"
                    "1910" -> "Inert Gas System"
                    "1911" -> "Crude Oil Washing"
                    "1912" -> "Static Electricity Precautions"
                    "1913", "1950" -> "Manifold Arrangements"
                    "1914" -> "Pump Rooms"
                    "1915", "1916", "1917", "1918", "1919", "1920", "1921", "1922", "1923", "1924", "1925", "1952", "2105" -> "Cargo Hoses"
                    "1926" -> "Cargo and Ballast Systems Chemical"
                    "1927", "1928", "1929", "1930", "1931", "1932", "1933", "1934", "1935", "1936", "1937", "1938", "1940" -> "Policies Procedures & Documentation"
                    "1939" -> "Cargo and ballast systems  gas"
                    "1941" -> "Stability and Cargo Loading Limitations"
                    "1942" -> "Cargo Operations and Related Safety Management"
                    "1943" -> "Cargo Handling and Monitoring Equipment"
                    "1944" -> "Cargo Compressor and Motor Rooms"
                    "1945" -> "Void Spaces and Seals - Type C Cargo Tanks"
                    "1946" -> "Void and Interbarrier Spaces and Seals - Other Cargo Tank Types"
                    "1947" -> "Inert Gas System"
                    "1948" -> "Pressure Relief and Venting Systems"
                    "1949" -> "Emergency Shut Down System"
                    "1951" -> "Safety Equipment"
                    "1953", "2106", "2107", "2133", "2134" -> "Cargo Lifting Equipment"
                    "1954" -> "LNG Carriers"
                    "1955", "1957" -> "Ship to Ship Transfer Operations - Gas"
                    "1956", "1958", "1959" -> "Mooring Procedures"
                    "1960" -> "Anchoring Equipment"
                    "1961" -> "Single Point Moorings"
                    "1962", "1963", "1964" -> "Emergency Towing Arrangements"
                    "1965", "1966", "1967" -> "Pilotage"
                    "1969" -> "Planned maintenance"
                    "1970", "2088" -> "Safety Management"
                    "1971" -> "Machinery status"
                    "1972", "1973", "1974", "1975", "1976" -> "Steering Compartment"
                    "1977" -> "Accommodation areas"
                    "1978" -> "General Information"
                    "2006", "2024" -> "All ships of 3000gt and upwards"
                    "2007" -> "Inert Gas Systems"
                    "2008", "2009", "2010" -> "Material Safety Datasheets (MSDS)"
                    "2011" -> "Navigation equipment {All vessels constructed before 1st July 2002}"
                    "2012" -> "All ships irrespective of size"
                    "2013", "2020" -> "All ships of 150gt and upwards"
                    "2014", "2022" -> "All ships of 300gt and upwards on international voyages"
                    "2015", "2023" -> "All ships of 500gt and upwards"
                    "2016", "2025" -> "All ships of 10000gt and upwards"
                    "2017" -> "All ships of 100000gt and upwards"
                    "2018" -> "Navigation equipment (All vessels constructed (i.e. keel laid) after  1st July 2002)"
                    "2019" -> "All ships irrespective of size"
                    "2021" -> "All ships of 300gt and upwards"
                    "2026" -> "All ships of 50000gt and upwards"
                    "2027", "2029", "2032", "2033", "2034", "2035", "2050", "2051", "2052", "2053", "2054", "2055", "2056", "2057", "2058", "2059", "2060", "2061", "2062", "2063", "2064", "2065", "2066", "2067", "2068", "2069" -> "Access"
                    "2028" -> "Venting Arrangements"
                    "2048", "2049" -> "Certification"
                    "2070", "2071" -> "Ullaging, Sampling and Closed Operations"
                    "2072", "2073", "2074", "2075" -> "Mooring Equipment Documentation and management"
                    "2076", "2077" -> "Safety Management and the Operator’s Procedures Manuals"
                    "2078" -> "Survey and Repair History"
                    "2079" -> "Anti-Pollution"
                    "2080" -> "Structure"
                    "2081" -> "Crew Qualifications"
                    "2082", "2083", "2084" -> "Drug and alcohol policy"
                    "2085" -> "Navigation equipment"
                    "2086", "2087" -> "Communications"
                    "2089"  -> "Gas analysing equipment"
                    "2090" -> "Hot work procedures"
                    "2091" -> "Emergency Towing Arrangements"
                    "2092" -> "Sample Arrangements"
                    "2093" -> "Cargo Operations and Deck Camera.Area Pollution Prevention"
                    "2094", "2095" -> "Ballast Water Management"
                    "2096" -> "Stability and Cargo Loading limitations"
                    "2097" -> "Cargo Operations and Related Safety Management"
                    "2098" -> "Ullaging Sampling and Closed Operations"
                    "2099" -> "Inert Gas Systems"
                    "2100" -> "Venting Arrangements"
                    "2101" -> "Static Electricity Precautions"
                    "2102" -> "Manifold Arrangements"
                    "2103" -> "Cargo Pump Room"
                    "2104" -> "Safety Equipment"
                    "2108" -> "Engine and steering compartments"
                    "2109", "2110" -> "Fire Fighting Equipment"
                    "2111" -> "Hull, superstructure and external weather decks"
                    "2112" -> "Electrical equipment"
                    "2113", "2114" -> "Internal spaces"
                    "2116", "2117" -> "Mooring"
                    "2118" -> "Navigation and Communications"
                    "2119" -> "Drills, Training and Familiarisation"
                    "2120" -> "Ullaging Sampling and Closed Operations"
                    "2121" -> "Emergency Towing Arrangements"
                    "2122" -> "Monitoring non-cargo spaces"
                    "2123" -> "VOC Management Plan"
                    "2124", "2125", "2126", "2127", "2128", "2129", "2130" -> "VOC Management Plan"
                    "2131", "2132" -> "Cyber Security"
                    "2135", "2136", "2137" -> "Life-saving equipment"
                    "2138", "2139", "2140" -> "Ship to Ship Transfer Operations - Petroleum"
                    "2141", "2142", "2143", "2144" -> "Extra Observations"
                    "2145", "2146" -> "General appearance and condition"
                    "2147", "2148", "2149", "2150" -> "Emergency Towing Arrangements"
                    "2151", "2152", "2153", "2154", "2155", "2156", "2157", "2158", "2159", "2160", "2161", "2162" -> "Certification"
                    "2163", "2164" -> "Part B: Dynamic Assessment"
                    "2165" -> "Part A: Static Assessment"
                    "2166", "2167", "2168", "2169", "2170", "2171" -> "Navigational Assessment and Audit"
                    "2180", "2186" -> "Company Policy"
                    "2182" -> "Passage Planning"
                    "2184" -> "Bridge Equipment"
                    "2185" -> "Forms and Checklists"
                    "2187" -> "Bridge team organisation"
                    "2188" -> "Duties"
                    "2189" -> "General Navigation"
                    "2190" -> "Passage Planning"
                    "2191" -> "Use and Understanding of Bridge Equipment"
                    "2192" -> "Pilotage"
                    else -> "Other"
                }

                if (questionsMap.isEmpty()) {
                    val list = mutableListOf<Questions>()
                    list.add(question)
                    questionsMap.put(category, list)
                } else {
                    var isInMap = false
                    for ((key, value) in questionsMap) {
                        //                  Log.d("My Log", "Category Filter key: $key, question: $question")
                        if (key == category) {
                            val list: MutableList<Questions> = value as MutableList<Questions>
                            list.add(question)
                            questionsMap[key] = list
                            isInMap = true
                            break
                        }
                    }
                    if (!isInMap) {
                        val list = mutableListOf<Questions>()
                        list.add(question)
                        questionsMap.put(category, list)
                    }
                }
            }
            return questionsMap
        }

        fun getCategoryList(questionsMap: Map<String, List<Questions>>): List<String> {
            val setCategory = mutableSetOf<String>()
            for ((key, value) in questionsMap) {
                setCategory.add(key)
            }
            val categoriesList = mutableListOf<String>()
            for (category in setCategory) {
                categoriesList.add(category)
            }
            return categoriesList
        }
    }


}