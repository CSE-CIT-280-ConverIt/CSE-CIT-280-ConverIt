

        public class Temperature {
            float f, c, k;


            public float FtoC(float f) {
                float c = (f - 32) * 5f / 9f;
                return c;
            }

            public float FtoK(float f) {
                float k = (f + 459.67f) * 5f / 9f;
                return k;
            }

            public float CtoF(float c) {
                float f = c * 9.0f / 5.0f + 32;
                return f;
            }

            public float CtoK(float c) {
                float k = c + 273.15f;
                return k;
            }

            public float KtoF(float k) {
                float f = k * 9f / 5f - 459.67f;
                return f;
            }

            public float KtoC(float k) {
                float c = k - 273.15f;
                return c;
            }

        }







