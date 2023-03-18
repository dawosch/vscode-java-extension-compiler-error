package com.example.demo;

public final class TestInput extends TestInputAbstract {
    private final String value;

    public TestInput(String value) {
        this.value = value;
    }

    @Override
    public String value() {
        return this.value;
    }

    public TestInput build() {
        return new TestInput(value);
    }

    public final TestInput withValue(String value) {
        String newValue = value;
        if (this.value.equals(newValue))
            return this;
        return new TestInput(newValue);
    }

}
