import java.util.Iterator;

public class Main {
    public static void main(String[] args) {
        SingleLinkList<Contact> contactList = new SingleLinkList<>();

        contactList.addToEnd(new Contact(123, "Васильев Евстахий Борисович", "+129381832"));
        contactList.addToEnd(new Contact(151, "Коновалов Степан Петрович", "+234432334"));
        contactList.addToEnd(new Contact(332, "Калинин Артём Валериевич", "+2234234423"));
        contactList.addToEnd(new Contact(432, "Предыбайло Григорий Анатолиевич", "+2342344234"));
        contactList.addToEnd(new Contact(556, "Степанов Мирослав Андреевич", "+6678877777"));

        //оператор фор ,что бы пройти по всей коллекции сразу
        for(Contact contact:contactList){
            System.out.println(contact);
        }

        contactList.reverce();
        System.out.println("______________________________");
        //проходимся уже по обращенному списку
        for(Contact contact:contactList){
            System.out.println(contact);
        }
    }
    static class Contact{
        int id;
        String name;
        String phone;

        public Contact(int id,String name,String phone){
            this.id = id;
            this.name = name;
            this.phone = phone;
        }

        @Override
        public String toString(){
            return "Contact{" +
                    "id="+id+
                    ", name='"+name+'\''+
                    ", phone='"+phone+'\''+
                    '}';
        }
    }

    //класс односвязный список
    //итератор для последовательный доступ к элементам списка
    public static class SingleLinkList<T> implements Iterable<T>{
        ListItem<T> head;//ссылка на голову списка
        ListItem<T> tail;//ссылка на хвост списка

        @Override
        public Iterator<T> iterator (){
            //итератор для последовательного прохода по коллекции
            return new Iterator<T>(){
                //указатель на текущий элемент
                //на котором находится итератор в данный момент
                ListItem<T> current = head;

                //функция haNext будет показывать
                //мы находимся в списке или уже на конце
                //в списке есть элементы если current != null
                @Override
                public boolean hasNext(){
                    return current != null;
                }


                //если не дошли до конца списка, значит еще элементы есть
                //что бы выдать следующий элемент мы возьмем данные из текущего
                //и сдвинем текущий элемент(присвоим current > current.next)
                //продвинемся на один элемент по списку и вернем данные
                @Override
                public T next(){
                    T data = current.data;
                    current = current.next;
                    return data;

                }

            };
        }

        //класс элемент списка
        private static class ListItem<T>{
            T data;
            ListItem<T> next;//ссылка на следующий элемент
        }

        //метод если список пустой
        public boolean isEmpty(){
            return head == null;
        }

        //реализация вставки нового элемента в конец списка
        public void addToEnd(T item){
            ListItem<T> newItem = new ListItem<>();//новый элемент списка
            newItem.data = item;

            //если список пустой
            //хвост и голова указывают на один и тот же элемент
            if(isEmpty()){
                head = newItem;
                tail = newItem;
            }else{
                tail.next = newItem;
                tail = newItem;
                //если список не пустой то задаем ссылку на новый элемент
                //этот элемент становится новым хвостом списка
            }
        }

        //проверка на наличие пустоты(есть хотя бы два элемента)
        public void reverce(){
            if(!isEmpty() && head.next!=null){
                tail=head;//если элементов всего 1,то обращенный список равен необращенному
                ListItem<T> current = head.next;
                head.next = null;
                //идем по списку до тех пор пока текущий элемент не равен null
                while(current !=null){
                    ListItem<T> next = current.next;//сохраняем ссылку на следующий элемент
                    current.next = head;//ссылке следующий элемент текущего элемента присваиваем head
                    head = current;//в хэд записываем current.Смещаем указатели на один элемент вперед
                    current = next;
                }
            }
        }
    }
}