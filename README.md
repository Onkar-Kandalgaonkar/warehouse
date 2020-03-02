# warehouse

Prerequisits:
Java 8+ and Maven should be installed on the system

Commands for building and running the projects:
1. Open terminal in the project root directory
2. Run the following command for starting shell script
   chmod 755 warehouse
   ./warehouse
   The application should get build and should run with the spring shell(CLI)
3. Use the following commands for testing the project(Also available in /resouces/shellcommands.txt)
   warehouse 6
   store 72527273070 White
   store 72527273071 Green
   store 72537113170 Purple
   store 72537113171 Black
   store 72537113172 Black
   store 72537113173 Green
   sell 4
   status
   store 82537113174 Purple
   store 82527273075 White
   product_codes_for_products_with_colour White
   slot_numbers_for_products_with_colour Green
   slot_number_for_product_code 82537113174
   slot_number_for_product_code 82532222174
