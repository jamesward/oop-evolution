

trait ToChar {
    fn to_char(&self) -> char;
}

impl ToChar for char {
    fn to_char(&self) -> char {
        return *self;
    }
}

impl ToChar for &str {
    fn to_char(&self) -> char {
        return self.chars().next().unwrap();
    }
}

impl <T : ToChar> ToChar for Vec<T> {
    fn to_char(&self) -> char {
        return self.iter().next().unwrap().to_char();
    }
}


fn sum<T : ToChar>(content: Vec<T>) -> char {
    let asciibase = 'a' as u8 - 1;   
    return content.iter().fold(
        asciibase,
        |acc, x| acc + ((x.to_char() as u8) - asciibase)) as char;
}


fn main() {
    println!("a + a = {}", sum(vec!['a', 'a']));
    println!("hello + annie = {}", sum(vec!["hello", "annie"]));
    println!("hello + annie mayor = {}", sum(vec![vec!["hello"], vec!["annie", "mayor"]]));
    println!("h + a m = {}", sum(vec![vec!['h'], vec!['a', 'm']]));
}
