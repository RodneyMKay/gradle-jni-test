#include <jni.h>
#include "JNIReflectionBenchmark.h"

JNIEXPORT jobject JNICALL Java_JNIReflectionBenchmark_executeMethod(JNIEnv* env, jclass clazz, jstring methodName) {
    const char* nativeString = env->GetStringUTFChars(methodName, nullptr);
    jmethodID methodId = env->GetStaticMethodID(clazz, nativeString, "()Ljava/lang/String;");
    env->ReleaseStringUTFChars(methodName, nativeString);
    return env->CallStaticObjectMethod(clazz, methodId);
}
