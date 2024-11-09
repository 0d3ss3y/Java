# Password Generator

A secure and customizable Java password generator that creates strong passwords based on user-defined criteria and security levels.

## Features

- Multiple security levels for different password complexity requirements
- Customizable password length (4-16 characters)
- Support for various character types:
    - Lowercase letters
    - Uppercase letters
    - Special characters
    - Numbers
- Cryptographically secure random number generation
- User-friendly command-line interface
- Input validation and error handling

## Security Levels

1. **Basic (Level 1)**
    - Basic length validation
    - Lowercase letters only
    - Suitable for less critical applications

2. **Intermediate (Level 2)**
    - Customizable character types
    - Choose from:
        - Uppercase letters
        - Special characters
        - Numbers
    - Recommended for general use

3. **Strong (Level 3)**
    - Includes all character types:
        - Lowercase letters
        - Uppercase letters
        - Special characters
        - Numbers
    - Recommended for sensitive applications

## Installation

1. Clone the repository:
```bash
git clone https://github.com/yourusername/password-generator.git
```

2. Navigate to the project directory:
```bash
cd password-generator
```

3. Compile the project:
```bash
javac org/side/*.java org/side/generatePassword/*.java
```

## Usage

1. Run the program:
```bash
java org.side.PasswordGeneratorUI
```

2. Follow the interactive prompts:
    - Select security level (1-3)
    - Enter desired password length (4-16 characters)
    - Choose character types (for Level 2)

### Example Usage

```
=== Password Generator ===

Password Rules and Requirements:
1. Password Characteristics:
   • Length: 4-16 characters
   • Optional uppercase letters
   • Optional special characters
   • Optional numbers
...

Select security level (1-3) [default: 1]: 2
Enter password length (4-16) [default: 8]: 12

Select character types to include:
[U]ppercase letters
[S]pecial characters
[N]umbers
Enter options (e.g., USN or press Enter for none): US

=== Generated Password ===
Kj#mP$nL&vQx
=========================
```

## Project Structure

```
org/
├── side/
│   ├── PasswordGeneratorUI.java    # Main UI class
│   └── generatePassword/
│       └── PasswordGenerator.java   # Core password generation logic
```

## Security Considerations

- Uses `SecureRandom` for cryptographically secure random number generation
- Implements proper input validation
- Ensures minimum password strength requirements
- No password storage - generates passwords only
- Follows security best practices for password generation

## Requirements

- Java 17 or higher
- JDK 17+ for compilation

## Contributing

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## Best Practices for Generated Passwords

1. **Use Strong Passwords**
    - Choose Level 3 for sensitive accounts
    - Use maximum allowed length
    - Include all character types

2. **Password Management**
    - Use different passwords for different accounts
    - Consider using a password manager
    - Change passwords periodically

3. **Additional Security**
    - Enable two-factor authentication when available
    - Never share passwords
    - Avoid using personal information in passwords

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## Acknowledgments

- Built with security best practices in mind
- Inspired by modern password requirements
- Designed for ease of use and security

## Version History

- 1.0.0
    - Initial release
    - Basic password generation functionality
    - Three security levels
    - Command-line interface


Project Link: [https://github.com/0d3ss3y/Java/tree/main/Phase%201/0.Password%20Generator/password](https://github.com/yourusername/password-generator)
