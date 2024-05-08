#version 150
// The game's render output
uniform sampler2D DiffuseSampler;
// The texture coordinate represented as a 2D vector (x,y)
in vec2 texCoord;
// The output color of each pixel represented as a 4D vector (r,g,b,a)
out vec4 fragColor;

void main() {
    vec4 original = texture(DiffuseSampler, texCoord);

    fragColor = vec4(1.0 - original.rgb, 1.0);
}