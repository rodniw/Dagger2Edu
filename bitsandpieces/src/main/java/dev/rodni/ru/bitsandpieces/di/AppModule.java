package dev.rodni.ru.bitsandpieces.di;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    @Provides
    static String string() {
        return "asdlfkj";
    }
}
