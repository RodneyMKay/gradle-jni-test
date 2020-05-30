#include <iostream>
#include <jni.h>
#include "HelloWorld.h"

JNIEXPORT void JNICALL Java_HelloWorld_sayHello(JNIEnv* env, jclass clazz) {
    std::cout << "Hello World!" << std::endl;
}

