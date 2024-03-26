#version 150
uniform float Time;
uniform vec2 InSize;

// The game's render output
uniform sampler2D DiffuseSampler;
// The texture coordinate represented as a 2D vector (x,y)
in vec2 texCoord;
// The output color of each pixel represented as a 4D vector (r,g,b,a)
out vec4 fragColor;

float rand(vec2 n) {
    return fract(sin(dot(n, vec2(12.9898,12.1414))) * 83758.5453);
}

float noise(vec2 n) {
    const vec2 d = vec2(0.0, 1.0);
    vec2 b = floor(n);
    vec2 f = mix(vec2(0.0), vec2(1.0), fract(n));
    return mix(mix(rand(b), rand(b + d.yx), f.x), mix(rand(b + d.xy), rand(b + d.yy), f.x), f.y);
}

vec3 ramp(float t) {
    return t <= .5 ? vec3( 1. - t * 1.4, .2, 1.05 ) / t : vec3( .3 * (1. - t) * 2., .2, 1.05 ) / t;
}

float fire(vec2 n) {
    return noise(n) + noise(n * 2.1) * .6 + noise(n * 5.4) * .42;
}


vec3 getLine(vec2 fc, mat2 mtx, float shift){
    float t = Time;
    vec2 uv = (fc / InSize.xy) * mtx;

    uv.x += uv.y < .5 ? 23.0 + t * .35 : -11.0 + t * .3;
    uv.y = abs(uv.y - shift);
    uv *= 10.0;

    float q = fire(uv - t * .013) / 2.0;
    vec2 r = vec2(fire(uv + q / 2.0 + t - uv.x - uv.y), fire(uv + q - t));
    vec3 color = vec3(1.0 / (pow(vec3(0.5, 0.0, .1) + 1.61, vec3(4.0))));

    float grad = pow((r.y + r.y) * max(.0, uv.y) + .1, 4.0);
    color = ramp(grad);
    color /= (1.50 + max(vec3(0), color));
    return color;
}

vec3 getLine(vec3 col, vec2 fc, mat2 mtx, float shift){
    float t = Time;
    vec2 uv = (fc / InSize.xy) * mtx;

    uv.x += uv.y < .5 ? 23.0 + t * .35 : -11.0 + t * .3;
    uv.y = abs(uv.y - shift);
    uv *= 5.0;

    float q = fire(uv - t * .013) / 2.0;
    vec2 r = vec2(fire(uv + q / 2.0 + t - uv.x - uv.y), fire(uv + q - t));
    vec3 color = vec3(1.0 / (pow(vec3(0.5, 0.0, .1) + 1.61, vec3(4.0))));

    float grad = pow((r.y + r.y) * max(.0, uv.y) + .1, 4.0);
    color = ramp(grad);
    color /= (1.50 + max(vec3(0), color));

    if(color.b < .00000005)
    color = vec3(.0);

    return mix(col, color, color.b);
}

void main() {
    vec2 uv = texCoord;
    vec3 color = vec3(0.);
    color = getLine(color, texCoord, mat2(1., 1., 0., 1.), 1.02);
    color = getLine(color, texCoord, mat2(1., 1., 1., 0.), 1.02);
    color = getLine(color, texCoord, mat2(1., 1., 0., 1.), -0.02);
    color = getLine(color, texCoord, mat2(1., 1., 1., 0.), -0.02);

    fragColor = vec4(color, 1.0);
}