/*
 *
 *  * The MIT License
 *  *
 *  * Copyright 2016 Shekhar Gulati <shekhargulati84@gmail.com>.
 *  *
 *  * Permission is hereby granted, free of charge, to any person obtaining a copy
 *  * of this software and associated documentation files (the "Software"), to deal
 *  * in the Software without restriction, including without limitation the rights
 *  * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *  * copies of the Software, and to permit persons to whom the Software is
 *  * furnished to do so, subject to the following conditions:
 *  *
 *  * The above copyright notice and this permission notice shall be included in
 *  * all copies or substantial portions of the Software.
 *  *
 *  * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *  * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *  * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *  * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *  * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *  * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 *  * THE SOFTWARE.
 *
 */

package strman;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

abstract class Ascii {
    public static final Map<String, List<String>> ascii = new HashMap<String, List<String>>() {{

        put("0", Arrays.asList("°", "₀", "۰"));
        put("1", Arrays.asList("¹", "₁", "۱"));
        put("2", Arrays.asList("²", "₂", "۲"));
        put("3", Arrays.asList("³", "₃", "۳"));
        put("4", Arrays.asList("⁴", "₄", "۴", "٤"));
        put("5", Arrays.asList("⁵", "₅", "۵", "٥"));
        put("6", Arrays.asList("⁶", "₆", "۶", "٦"));
        put("7", Arrays.asList("⁷", "₇", "۷"));
        put("8", Arrays.asList("⁸", "₈", "۸"));
        put("9", Arrays.asList("⁹", "₉", "۹"));
        put("a", Arrays.asList("à", "á", "ả", "ã", "ạ", "ă", "ắ", "ằ", "ẳ", "ẵ", "ặ", "â", "ấ", "ầ", "ẩ", "ẫ", "ậ",
                "ā", "ą", "å", "α", "ά", "ἀ", "ἁ", "ἂ", "ἃ", "ἄ", "ἅ", "ἆ", "ἇ", "ᾀ", "ᾁ", "ᾂ", "ᾃ",
                "ᾄ", "ᾅ", "ᾆ", "ᾇ", "ὰ", "ά", "ᾰ", "ᾱ", "ᾲ", "ᾳ", "ᾴ", "ᾶ", "ᾷ", "а", "أ", "အ", "ာ",
                "ါ", "ǻ", "ǎ", "ª", "ა", "अ", "ا"));
        put("b", Arrays.asList("б", "β", "Ъ", "Ь", "ب", "ဗ", "ბ"));
        put("c", Arrays.asList("ç", "ć", "č", "ĉ", "ċ"));
        put("d", Arrays.asList("ď", "ð", "đ", "ƌ", "ȡ", "ɖ", "ɗ", "ᵭ", "ᶁ", "ᶑ", "д", "δ", "د", "ض", "ဍ", "ဒ", "დ"));
        put("e", Arrays.asList("é", "è", "ẻ", "ẽ", "ẹ", "ê", "ế", "ề", "ể", "ễ", "ệ", "ë", "ē", "ę", "ě", "ĕ", "ė",
                "ε", "έ", "ἐ", "ἑ", "ἒ", "ἓ", "ἔ", "ἕ", "ὲ", "έ", "е", "ё", "э", "є", "ə", "ဧ", "ေ",
                "ဲ", "ე", "ए", "إ", "ئ"));
        put("f", Arrays.asList("ф", "φ", "ف", "ƒ", "ფ"));
        put("g", Arrays.asList("ĝ", "ğ", "ġ", "ģ", "г", "ґ", "γ", "ဂ", "გ", "گ"));
        put("h", Arrays.asList("ĥ", "ħ", "η", "ή", "ح", "ه", "ဟ", "ှ", "ჰ"));
        put("i", Arrays.asList("í", "ì", "ỉ", "ĩ", "ị", "î", "ï", "ī", "ĭ", "į", "ı", "ι", "ί", "ϊ", "ΐ", "ἰ", "ἱ",
                "ἲ", "ἳ", "ἴ", "ἵ", "ἶ", "ἷ", "ὶ", "ί", "ῐ", "ῑ", "ῒ", "ΐ", "ῖ", "ῗ", "і", "ї", "и",
                "ဣ", "ိ", "ီ", "ည်", "ǐ", "ი", "इ", "ی"));
        put("j", Arrays.asList("ĵ", "ј", "Ј", "ჯ", "ج"));
        put("k", Arrays.asList("ķ", "ĸ", "к", "κ", "Ķ", "ق", "ك", "က", "კ", "ქ", "ک"));
        put("l", Arrays.asList("ł", "ľ", "ĺ", "ļ", "ŀ", "л", "λ", "ل", "လ", "ლ"));
        put("m", Arrays.asList("м", "μ", "م", "မ", "მ"));
        put("n", Arrays.asList("ñ", "ń", "ň", "ņ", "ŉ", "ŋ", "ν", "н", "ن", "န", "ნ"));
        put("o", Arrays.asList("ó", "ò", "ỏ", "õ", "ọ", "ô", "ố", "ồ", "ổ", "ỗ", "ộ", "ơ", "ớ", "ờ", "ở", "ỡ", "ợ",
                "ø", "ō", "ő", "ŏ", "ο", "ὀ", "ὁ", "ὂ", "ὃ", "ὄ", "ὅ", "ὸ", "ό", "о", "و", "θ", "ို",
                "ǒ", "ǿ", "º", "ო", "ओ"));
        put("p", Arrays.asList("п", "π", "ပ", "პ", "پ"));
        put("q", Arrays.asList("ყ"));
        put("r", Arrays.asList("ŕ", "ř", "ŗ", "р", "ρ", "ر", "რ"));
        put("s", Arrays.asList("ś", "š", "ş", "с", "σ", "ș", "ς", "س", "ص", "စ", "ſ", "ს"));
        put("t", Arrays.asList("ť", "ţ", "т", "τ", "ț", "ت", "ط", "ဋ", "တ", "ŧ", "თ", "ტ"));
        put("u", Arrays.asList("ú", "ù", "ủ", "ũ", "ụ", "ư", "ứ", "ừ", "ử", "ữ", "ự", "û", "ū", "ů", "ű", "ŭ", "ų",
                "µ", "у", "ဉ", "ု", "ူ", "ǔ", "ǖ", "ǘ", "ǚ", "ǜ", "უ", "उ"));
        put("v", Arrays.asList("в", "ვ", "ϐ"));
        put("w", Arrays.asList("ŵ", "ω", "ώ", "ဝ", "ွ"));
        put("x", Arrays.asList("χ", "ξ"));
        put("y", Arrays.asList("ý", "ỳ", "ỷ", "ỹ", "ỵ", "ÿ", "ŷ", "й", "ы", "υ", "ϋ", "ύ", "ΰ", "ي", "ယ"));
        put("z", Arrays.asList("ź", "ž", "ż", "з", "ζ", "ز", "ဇ", "ზ"));
        put("aa", Arrays.asList("ع", "आ", "آ"));
        put("ae", Arrays.asList("ä", "æ", "ǽ"));
        put("ai", Arrays.asList("ऐ"));
        put("at", Arrays.asList("@"));
        put("ch", Arrays.asList("ч", "ჩ", "ჭ", "چ"));
        put("dj", Arrays.asList("ђ", "đ"));
        put("dz", Arrays.asList("џ", "ძ"));
        put("ei", Arrays.asList("ऍ"));
        put("gh", Arrays.asList("غ", "ღ"));
        put("ii", Arrays.asList("ई"));
        put("ij", Arrays.asList("ĳ"));
        put("kh", Arrays.asList("х", "خ", "ხ"));
        put("lj", Arrays.asList("љ"));
        put("nj", Arrays.asList("њ"));
        put("oe", Arrays.asList("ö", "œ", "ؤ"));
        put("oi", Arrays.asList("ऑ"));
        put("oii", Arrays.asList("ऒ"));
        put("ps", Arrays.asList("ψ"));
        put("sh", Arrays.asList("ш", "შ", "ش"));
        put("shch", Arrays.asList("щ"));
        put("ss", Arrays.asList("ß"));
        put("sx", Arrays.asList("ŝ"));
        put("th", Arrays.asList("þ", "ϑ", "ث", "ذ", "ظ"));
        put("ts", Arrays.asList("ц", "ც", "წ"));
        put("ue", Arrays.asList("ü"));
        put("uu", Arrays.asList("ऊ"));
        put("ya", Arrays.asList("я"));
        put("yu", Arrays.asList("ю"));
        put("zh", Arrays.asList("ж", "ჟ", "ژ"));
        put("(c)", Arrays.asList("©"));
        put("A", Arrays.asList("Á", "À", "Ả", "Ã", "Ạ", "Ă", "Ắ", "Ằ", "Ẳ", "Ẵ", "Ặ", "Â", "Ấ", "Ầ", "Ẩ", "Ẫ", "Ậ", "Å",
                "Ā", "Ą", "Α", "Ά", "Ἀ", "Ἁ", "Ἂ", "Ἃ", "Ἄ", "Ἅ", "Ἆ", "Ἇ", "ᾈ", "ᾉ", "ᾊ", "ᾋ", "ᾌ", "ᾍ",
                "ᾎ", "ᾏ", "Ᾰ", "Ᾱ", "Ὰ", "Ά", "ᾼ", "А", "Ǻ", "Ǎ"));
        put("B", Arrays.asList("Б", "Β", "ब"));
        put("C", Arrays.asList("Ç", "Ć", "Č", "Ĉ", "Ċ"));
        put("D", Arrays.asList("Ď", "Ð", "Đ", "Ɖ", "Ɗ", "Ƌ", "ᴅ", "ᴆ", "Д", "Δ"));
        put("E", Arrays.asList("É", "È", "Ẻ", "Ẽ", "Ẹ", "Ê", "Ế", "Ề", "Ể", "Ễ", "Ệ", "Ë", "Ē", "Ę", "Ě", "Ĕ", "Ė", "Ε",
                "Έ", "Ἐ", "Ἑ", "Ἒ", "Ἓ", "Ἔ", "Ἕ", "Έ", "Ὲ", "Е", "Ё", "Э", "Є", "Ə"));
        put("F", Arrays.asList("Ф", "Φ"));
        put("G", Arrays.asList("Ğ", "Ġ", "Ģ", "Г", "Ґ", "Γ"));
        put("H", Arrays.asList("Η", "Ή", "Ħ"));
        put("I", Arrays.asList("Í", "Ì", "Ỉ", "Ĩ", "Ị", "Î", "Ï", "Ī", "Ĭ", "Į", "İ", "Ι", "Ί", "Ϊ", "Ἰ", "Ἱ", "Ἳ", "Ἴ",
                "Ἵ", "Ἶ", "Ἷ", "Ῐ", "Ῑ", "Ὶ", "Ί", "И", "І", "Ї", "Ǐ", "ϒ"));
        put("K", Arrays.asList("К", "Κ"));
        put("L", Arrays.asList("Ĺ", "Ł", "Л", "Λ", "Ļ", "Ľ", "Ŀ", "ल"));
        put("M", Arrays.asList("М", "Μ"));
        put("N", Arrays.asList("Ń", "Ñ", "Ň", "Ņ", "Ŋ", "Н", "Ν"));
        put("O", Arrays.asList("Ó", "Ò", "Ỏ", "Õ", "Ọ", "Ô", "Ố", "Ồ", "Ổ", "Ỗ", "Ộ", "Ơ", "Ớ", "Ờ", "Ở", "Ỡ", "Ợ", "Ø",
                "Ō", "Ő", "Ŏ", "Ο", "Ό", "Ὀ", "Ὁ", "Ὂ", "Ὃ", "Ὄ", "Ὅ", "Ὸ", "Ό", "О", "Θ", "Ө", "Ǒ", "Ǿ"));
        put("P", Arrays.asList("П", "Π"));
        put("R", Arrays.asList("Ř", "Ŕ", "Р", "Ρ", "Ŗ"));
        put("S", Arrays.asList("Ş", "Ŝ", "Ș", "Š", "Ś", "С", "Σ"));
        put("T", Arrays.asList("Ť", "Ţ", "Ŧ", "Ț", "Т", "Τ"));
        put("U", Arrays.asList("Ú", "Ù", "Ủ", "Ũ", "Ụ", "Ư", "Ứ", "Ừ", "Ử", "Ữ", "Ự", "Û", "Ū", "Ů", "Ű", "Ŭ", "Ų", "У",
                "Ǔ", "Ǖ", "Ǘ", "Ǚ", "Ǜ"));
        put("V", Arrays.asList("В"));
        put("W", Arrays.asList("Ω", "Ώ", "Ŵ"));
        put("X", Arrays.asList("Χ", "Ξ"));
        put("Y", Arrays.asList("Ý", "Ỳ", "Ỷ", "Ỹ", "Ỵ", "Ÿ", "Ῠ", "Ῡ", "Ὺ", "Ύ", "Ы", "Й", "Υ", "Ϋ", "Ŷ"));
        put("Z", Arrays.asList("Ź", "Ž", "Ż", "З", "Ζ"));
        put("AE", Arrays.asList("Ä", "Æ", "Ǽ"));
        put("CH", Arrays.asList("Ч"));
        put("DJ", Arrays.asList("Ђ"));
        put("DZ", Arrays.asList("Џ"));
        put("GX", Arrays.asList("Ĝ"));
        put("HX", Arrays.asList("Ĥ"));
        put("IJ", Arrays.asList("Ĳ"));
        put("JX", Arrays.asList("Ĵ"));
        put("KH", Arrays.asList("Х"));
        put("LJ", Arrays.asList("Љ"));
        put("NJ", Arrays.asList("Њ"));
        put("OE", Arrays.asList("Ö", "Œ"));
        put("PS", Arrays.asList("Ψ"));
        put("SH", Arrays.asList("Ш"));
        put("SHCH", Arrays.asList("Щ"));
        put("SS", Arrays.asList("ẞ"));
        put("TH", Arrays.asList("Þ"));
        put("TS", Arrays.asList("Ц"));
        put("UE", Arrays.asList("Ü"));
        put("YA", Arrays.asList("Я"));
        put("YU", Arrays.asList("Ю"));
        put("ZH", Arrays.asList("Ж"));
        put(" ", Arrays.asList("\\xC2\\xA0", "\\xE2\\x80\\x80", "\\xE2\\x80\\x81", "\\xE2\\x80\\x82", "\\xE2\\x80\\x83",
                "\\xE2\\x80\\x84", "\\xE2\\x80\\x85", "\\xE2\\x80\\x86", "\\xE2\\x80\\x87", "\\xE2\\x80\\x88",
                "\\xE2\\x80\\x89", "\\xE2\\x80\\x8A", "\\xE2\\x80\\xAF", "\\xE2\\x81\\x9F", "\\xE3\\x80\\x80"));


    }};

}
