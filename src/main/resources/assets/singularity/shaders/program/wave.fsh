#version 150
uniform float Time;
uniform vec2 InSize;

// The game's render output
uniform sampler2D DiffuseSampler;
// The texture coordinate represented as a 2D vector (x,y)
in vec2 texCoord;
// The output color of each pixel represented as a 4D vector (r,g,b,a)
out vec4 fragColor;

void main()
{
    vec3 WaveParams = vec3(10.0, 0.8, 0.1 );

    float ratio = InSize.y / InSize.x;

    vec2 WaveCentre = vec2(0.5, 0.5);

    float Dist = distance(texCoord, WaveCentre);

    vec4 Color = texture(DiffuseSampler, texCoord);

    //Only distort the pixels within the parameter distance from the centre
    if ((Dist <= ((Time) + (WaveParams.z))) &&
    (Dist >= ((Time) - (WaveParams.z))))
    {
        //The pixel offset distance based on the input parameters
        float Diff = (Dist - Time);
        float ScaleDiff = (1.0 - pow(abs(Diff * WaveParams.x), WaveParams.y));
        float DiffTime = (Diff * ScaleDiff);

        // The direction of the distortion
        vec2 DiffTexCoord = normalize(texCoord - WaveCentre);

        // Perform the distortion and reduce the effect over time
        vec2 newTexCoord = texCoord + ((DiffTexCoord * DiffTime) / (Time * Dist * 40.0));
        Color = texture(DiffuseSampler, newTexCoord);

        //Blow out the color and reduce the effect over time
        Color += (Color * ScaleDiff) / (Time * Dist * 40.0);
    }

    fragColor = Color;
}
