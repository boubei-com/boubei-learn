package jinhe.lt.thinkingInJava.rtti;

import java.util.Random;

class Pet {
}

class Dog extends Pet {
}

class Pug extends Dog {
}

class Cat extends Pet {
}

class Rodent extends Pet {
}

class Gerbil extends Rodent {
}

class Hamster extends Rodent {
}

class Counter {
	int i;

	public String toString() {
		return Integer.toString(i);
	}
}

class AssociativeArray {
	private Object[][] pairs;

	private int index;

	public AssociativeArray(int length) {
		pairs = new Object[length][2];
	}

	public void put(Object key, Object value) {
		if (index >= pairs.length)
			throw new ArrayIndexOutOfBoundsException();
		pairs[index++] = new Object[] { key, value };
	}

	public Object get(Object key) {
		for (int i = 0; i < index; i++)
			if (key.equals(pairs[i][0]))
				return pairs[i][1];
		throw new RuntimeException("Failed to find key");
	}

	public String toString() {
		String result = "";
		for (int i = 0; i < index; i++) {
			result += pairs[i][0] + " : " + pairs[i][1];
			if (i < index - 1)
				result += "\n";
		}
		return result;
	}
}

public class PetCount {
	private static Random rand = new Random();

	static String[] typenames = { "Pet", "Dog", "Pug", "Cat", "Rodent",
			"Gerbil", "Hamster", };

	public static void main(String[] args) {
		Object[] pets = new Object[15];
		
//		Class[] petTypes = { Pet.class, Dog.class, Pug.class, Cat.class,
//				Rodent.class, Gerbil.class, Hamster.class, };
		try {
			Class<?>[] petTypes = { Class.forName("com.jinpj.rtti.Dog"),
					Class.forName("com.jinpj.rtti.Pug"),
					Class.forName("com.jinpj.rtti.Cat"),
					Class.forName("com.jinpj.rtti.Rodent"),
					Class.forName("com.jinpj.rtti.Gerbil"),
					Class.forName("com.jinpj.rtti.Hamster"), };

			for (int i = 0; i < pets.length; i++)
				pets[i] = petTypes[rand.nextInt(petTypes.length)].newInstance();

		} catch (InstantiationException e) {
			System.out.println("Cannot instantiate");
			System.exit(1);
		} catch (IllegalAccessException e) {
			System.out.println("Cannot access");
			System.exit(1);
		} catch (ClassNotFoundException e) {
			System.out.println("Cannot find class");
			System.exit(1);
		}
		AssociativeArray map = new AssociativeArray(typenames.length);
		for (int i = 0; i < typenames.length; i++)
			map.put(typenames[i], new Counter());

		for (int i = 0; i < pets.length; i++) {
			Object o = pets[i];
			if (o instanceof Pet)
				((Counter) map.get("Pet")).i++;
			if (o instanceof Dog)
				((Counter) map.get("Dog")).i++;
			if (o instanceof Pug)
				((Counter) map.get("Pug")).i++;
			if (o instanceof Cat)
				((Counter) map.get("Cat")).i++;
			if (o instanceof Rodent)
				((Counter) map.get("Rodent")).i++;
			if (o instanceof Gerbil)
				((Counter) map.get("Gerbil")).i++;
			if (o instanceof Hamster)
				((Counter) map.get("Hamster")).i++;
		}

		// for(int i = 0; i < pets.length; i++) {
		// Object o = pets[i];
		// // Using Class.isInstance() to eliminate
		// // individual instanceof expressions:
		// for(int j = 0; j < petTypes.length; ++j)
		// if(petTypes[j].isInstance(o))
		// ((Counter)map.get(petTypes[j].toString())).i++;
		// }

		for (int i = 0; i < pets.length; i++)
			System.out.println(pets[i].getClass());

		System.out.println(map);
	}
}