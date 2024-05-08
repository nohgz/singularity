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

    // Calculate the negative color (invert grayscale)
    vec4 negativeColor = 1 - grayscaleColor;

    // Interpolate between the grayscale and the negative grayscale using the Time variable
    vec4 transitionColor = mix(grayscaleColor, negativeColor, abs(sin(Time * 6.28318531f))); // Lerp between grayscale and negative

    // Output the final color with alpha set to 1.0
    fragColor = vec4(transitionColor);
}