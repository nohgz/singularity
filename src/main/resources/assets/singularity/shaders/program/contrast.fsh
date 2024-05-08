#version 150
// The game's render output
uniform sampler2D DiffuseSampler;
// The texture coordinate represented as a 2D vector (x,y)
in vec2 texCoord;
// The output color of each pixel represented as a 4D vector (r,g,b,a)
out vec4 fragColor;

void main() {
    vec4 original = texture(DiffuseSampler, texCoord);

    // Convert original color components to either 0 or 1 based on whether they're greater than 0.5
    original.r = step(0.5, original.r);
    original.g = step(0.5, original.g);
    original.b = step(0.5, original.b);

    fragColor = vec4(original.rgb, 1.0);
}

