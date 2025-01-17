package com.example.sleepapp.SleepDiary.Tips;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sleepapp.R;

import java.util.ArrayList;
import java.util.List;

public class TipsActivity extends AppCompatActivity {

    private RecyclerView tipsRecyclerView;
    private CardView closeButton;
    private TextView categoryTitle;
    private List<String> tipsList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommendations);

        tipsRecyclerView = findViewById(R.id.tipsRecyclerView);
        closeButton = findViewById(R.id.closeButton);
        categoryTitle = findViewById(R.id.category_title);

        String category = getIntent().getStringExtra("category");
        categoryTitle.setText(category);
        tipsList = getTipsForCategory(category);

        TipAdapter adapter = new TipAdapter(tipsList);
        tipsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        tipsRecyclerView.setAdapter(adapter);

        closeButton.setOnClickListener(view -> finish());
    }


    private List<String> getTipsForCategory(String category) {
        List<String> allTips = new ArrayList<>();

        if (category.equals("Режим и гигиена сна")) {
            allTips.add("Старайтесь ложиться спать и просыпаться в одно и то же время каждый день, даже в выходные, чтобы наладить внутренние биологические часы.");
            allTips.add("Создайте расслабляющий ритуал перед сном, например, тёплая ванна, чтение книги или медитация.");
            allTips.add("Избегайте длительного дневного сна, особенно во второй половине дня, чтобы не нарушить ночной сон. Если нужна кратковременная передышка, ограничьтесь 20-30 минутами.");
            allTips.add("Не используйте кровать для работы, просмотра телевизора или других занятий, которые не связаны со сном. Пусть кровать ассоциируется у вас только со сном.");
            allTips.add("Старайтесь придерживаться постоянного времени отхода ко сну, чтобы организм вырабатывал гормоны сна в нужное время.");
            allTips.add("Ограничьте использование гаджетов (телефоны, планшеты) перед сном, так как синий свет экранов может мешать выработке мелатонина, гормона сна.");
            allTips.add("Если не удается заснуть в течение 20 минут, встаньте с кровати и займитесь чем-нибудь расслабляющим, а затем снова ложитесь спать.");

        } else if (category.equals("Питание и напитки")) {
            allTips.add("Ужинайте за 2-3 часа до сна лёгкой пищей, чтобы не перегружать желудок перед сном. Избегайте жирной и острой пищи.");
            allTips.add("Ограничьте потребление кофеина, никотина и алкоголя, особенно во второй половине дня и вечером. Эти вещества могут ухудшить качество сна.");
            allTips.add("Выпейте тёплое молоко или травяной чай без кофеина перед сном. Тёплые напитки могут способствовать расслаблению.");
            allTips.add("Избегайте обильного питья жидкости перед сном, чтобы не просыпаться ночью из-за позывов в туалет.");
            allTips.add("Пейте достаточно воды в течение дня, чтобы избежать обезвоживания, но ограничьте потребление ближе к вечеру.");
            allTips.add("Старайтесь не ложиться спать голодным, но также избегайте переедания на ночь. Лёгкий перекус может помочь заснуть.");
            allTips.add("Обратите внимание на продукты, богатые магнием (зеленые овощи, орехи), так как магний способствует расслаблению мышц и нервной системы.");

        } else if (category.equals("Активность и образ жизни")) {
            allTips.add("Регулярные физические упражнения, но не позже, чем за 3-4 часа до сна. Регулярная активность улучшает сон, но интенсивные тренировки перед сном могут вызвать бессонницу.");
            allTips.add("Проводите время на свежем воздухе и получайте достаточно солнечного света в течение дня. Это помогает регулировать циркадные ритмы.");
            allTips.add("Совершайте спокойную прогулку на свежем воздухе перед сном. Это может помочь расслабиться и заснуть.");
            allTips.add("Избегайте переутомления и чрезмерного напряжения, особенно в вечернее время.");
            allTips.add("Постепенно отказывайтесь от вредных привычек, таких как курение и употребление алкоголя, чтобы улучшить сон и общее состояние здоровья.");
            allTips.add("Старайтесь проводить время на свежем воздухе днем и по возможности избегать работы ночью. ");
            allTips.add("Занимайтесь деятельностью, которая вам нравится и приносит радость, это поможет снизить уровень стресса и улучшить сон.");

        } else if (category.equals("Комфортная среда")) {
            allTips.add("Обеспечьте в спальне темноту, тишину и прохладу. Используйте плотные шторы или маску для сна, беруши или белый шум, если это необходимо.");
            allTips.add("Оптимальная температура в спальне составляет около 18-20 градусов Цельсия. Убедитесь, что вам не слишком жарко и не слишком холодно.");
            allTips.add("Используйте удобную кровать, матрас и подушку, которые подходят именно вам. Убедитесь, что постельное бельё чистое и комфортное.");
            allTips.add("Обеспечьте приток свежего воздуха в спальню. Регулярно проветривайте комнату перед сном.");
            allTips.add("Минимизируйте электромагнитное излучение в спальне. Выключайте или убирайте из комнаты электронные устройства, если это возможно.");
            allTips.add("Поддерживайте порядок в спальне, так как чистая и ухоженная обстановка способствует расслаблению.");
            allTips.add("Используйте ароматерапию. Ароматы лаванды, ромашки или сандала могут помочь расслабиться и подготовиться ко сну.");

        } else if (category.equals("Психологическая подготовка")) {
            allTips.add("Используйте техники релаксации, такие как глубокое дыхание, медитация, йога или прогрессивная мышечная релаксация, чтобы снизить уровень стресса.");
            allTips.add("Ведите дневник перед сном, чтобы выплеснуть мысли и эмоции, которые могут мешать сну. Записывайте всё, что вас беспокоит, чтобы успокоить ум.");
            allTips.add("Старайтесь настроиться на позитивный лад перед сном, отвлекаясь от негативных мыслей. Попробуйте думать о приятных моментах или строить планы на следующий день.");
            allTips.add("Слушайте успокаивающую музыку или звуки природы, такие как шум дождя или пение птиц, чтобы создать благоприятную атмосферу для сна.");
            allTips.add("Если у вас есть проблемы со сном, не стесняйтесь обращаться за консультацией к специалисту (терапевту, сомнологу). Профессиональная помощь может помочь вам найти корень проблемы.");
            allTips.add("Практикуйте осознанность. Старайтесь быть в настоящем моменте и не зацикливаться на прошлых или будущих событиях. Это поможет расслабиться и заснуть.");
            allTips.add("Попробуйте использовать техники визуализации. Представляйте себе умиротворяющие картины, это поможет отвлечься от проблем и заснуть.");
        }
        return allTips;
    }

}
