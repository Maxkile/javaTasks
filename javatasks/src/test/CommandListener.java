package test;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Method;

@Retention(RetentionPolicy.RUNTIME) //Указывает, что наша Аннотация может использована во время выполнения через Reflection (нам как раз это нужно).
//@Target(ElementType.METHOD) //Указывает, что целью нашей Аннотации является метод (не класс, не переменная, не поле, а именно метод).
@interface Command //Описание. Заметим, что перед interface стоит @;
{
    String name(); //Команда за которую будет отвечать функция (например "привет");

    String args(); //Аргументы команды, использоваться будут для вывода списка команд

    int minArgs() default 0; //Минимальное количество аргументов, сразу присвоили 0 (логично)

    String desc(); //Описание, тоже для списка

    int maxArgs() default Integer.MAX_VALUE; //Максимальное число аргументов. Вцелом необязательно, но тоже можно использовать

    boolean showInHelp() default true; //Показывать ли команду в списке (вовсе необязательная строка, но мало ли, пригодится!)

    String[] aliases(); //Какие команды будут считаться эквивалентными нашей (Например для "привет", это может быть "Здаров", "Прив" и т.д., под каждый случай заводить функцию - не рационально

}

public class CommandListener
{
    @Command(name = "привет",
            args = "",
            desc = "Будь культурным, поздоровайся",
            showInHelp = false,
            aliases = {"здаров"})
    public void hello(String[] args)
    {
        //Какой-то функционал, на Ваше усмотрение.
    }

    @Command(name = "пока",
            args = "",
            desc = "",
            aliases = {"удачи"})
    public void bie(String[] args)
    {
        // Функционал
    }

    @Command(name = "помощь",
            args = "",
            desc = "Выводит список команд",
            aliases = {"help", "команды"})
    public String help(String[] args)
    {
        StringBuilder sb = new StringBuilder("Список команд: \n");
        for (Method m : this.getClass().getDeclaredMethods())
        {
            if (m.isAnnotationPresent(Command.class))
            {
                Command com = m.getAnnotation(Command.class);
                if (com.showInHelp()) //Если нужно показывать команду в списке.
                {
                    sb.append("Бот, ").append(com.name()).append(" ").append(com.args()).append(" - ").append(com.desc()).append("\n");
                }
            }
        }
        //Отправка sb.toString();
        return sb.toString();

    }

    public static void main(String[] args) {
        CommandListener list = new CommandListener();
        String[] arr = {"First","Second"};
        System.out.println(list.help(arr));
    }
}
