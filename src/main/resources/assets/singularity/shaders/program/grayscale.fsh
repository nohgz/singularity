#version 150
// The game's render output
uniform sampler2D DiffuseSampler;
uniform float Time;

// The texture coordinate represented as a 2D vector (x,y)
in vec2 texCoord;
// The output color of each pixel represented as a 4D vector (r,g,b,a)
out vec4 fragColor;


vec4 generic_desaturate(vec3 color, float factor)
{
    vec3 lum = vec3(0.299, 0.587, 0.114);
    vec3 gray = vec3(dot(lum, color));
    return vec4(mix(color, gray, factor), 1.0);
}

void main() {
    // Get the original color from the texture
    vec4 originalColor = texture(DiffuseSampler, texCoord);

    // Convert the original color to grayscale
    vec4 grayscaleColor = generic_desaturate(originalColor.rgb,1);

    fragColor = vec4(grayscaleColor);
}